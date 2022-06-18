package com.training.MusicPlayer.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.training.MusicPlayer.models.Song;
import com.training.MusicPlayer.models.SongPage;
import com.training.MusicPlayer.models.SongSourceUpload;
import com.training.MusicPlayer.models.SongThumbnailUpload;
import com.training.MusicPlayer.repositories.SongRepository;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.*;

@Service
public class SongService {
    @Autowired
    private SongRepository repository;
    @Autowired
    private MongoTemplate mongoTemplate;
    private final Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dungtd",
            "api_key", "553685374214836",
            "api_secret", "QLOlTiPPPESG9iyQhzG634GfhBQ"));
    private static final Logger logger = LoggerFactory.getLogger(SongService.class);


    public List<Song> findAll() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.ASC, "updateAt"));
        return mongoTemplate.find(query, Song.class, "song");
    }

    public Optional<Song> findById(String id) {
        return repository.findById(id);
    }

    public String save(Song song) {
        repository.save(song);
        return song.getName();
    }

    public SongPage getPage(String name, int index, Integer limit, Pageable pageable) {
        logger.info("Getting page: " + index + " and limit: " + limit + " and name: " + name);

        Query query = new Query();
        query.with(pageable);
        if (name != null) {
            logger.info("Have name: " + name);
            query.addCriteria(Criteria.where("name").regex(name, "i"));
        }

        query.with(Sort.by(Sort.Direction.DESC, "updateAt"));

        SongPage songPage = new SongPage(mongoTemplate.find(query, Song.class, "song"), pageable);

        return songPage;
    }

    public Song editSong(Song song) {
        logger.info("Editing song: " + song.getName());
        Optional<Song> songToUpdate = repository.findById(song.getId());

        if (songToUpdate.isPresent()) {
            songToUpdate.get().updateData(song);
            repository.save(songToUpdate.get());
            return songToUpdate.get();
        } else {
            return null;
        }

    }

    public String delete(Song song) throws IOException {
        logger.info("Deleting song: " + song.getName());
        Optional<Song> songToDelete = repository.findById(song.getId());

        Map deleteResult = null;

        if (songToDelete.get().getSrcId() != null) {
            deleteResult = cloudinary.uploader().destroy(songToDelete.get().getSrcId(),  ObjectUtils.asMap("resource_type", "video"));

            logger.info(deleteResult.toString());
        }

        if (songToDelete.get().getThumbnailId() != null) {
            deleteResult = cloudinary.uploader().destroy(songToDelete.get().getThumbnailId(),  ObjectUtils.asMap("resource_type", "image"));

            logger.info(deleteResult.toString());
        }

        repository.delete(songToDelete.get());
        return "success";
    }

    public List<Song> getRelatedSong(String songId) {
        logger.info("Getting next song for song: " + songId);
        List<Song> related = new ArrayList<>();
        Song song = mongoTemplate.findById(songId, Song.class, "song");
        if (song == null) {
            return null;
        }
        Query queryNext = new Query();
        queryNext.addCriteria(Criteria.where("updateAt").gt(song.getUpdateAt()));
        queryNext.with(Sort.by(Sort.Direction.ASC, "updateAt"));
        List<Song> nextSongs = mongoTemplate.find(queryNext, Song.class, "song");


        if (nextSongs.size() > 0) {
            related.add(nextSongs.get(0));
        } else {
            queryNext = new Query();
            queryNext.with(Sort.by(Sort.Direction.ASC, "updateAt"));
            related.add(mongoTemplate.find(queryNext, Song.class, "song").get(0));
        }

        Query queryPrevious = new Query();
        queryPrevious.addCriteria(Criteria.where("updateAt").lt(song.getUpdateAt()));
        queryPrevious.with(Sort.by(Sort.Direction.DESC, "updateAt"));
        List<Song> previousSongs = mongoTemplate.find(queryPrevious, Song.class, "song");

        if (previousSongs.size() > 0) {
            related.add(previousSongs.get(0));
        } else {
            queryPrevious = new Query();
            queryPrevious.with(Sort.by(Sort.Direction.DESC, "updateAt"));
            related.add(mongoTemplate.find(queryPrevious, Song.class, "song").get(0));
        }

        logger.info("Result:" + related);
        return related;
    }

    public Song uploadSongSource(Song song, @ModelAttribute SongSourceUpload songUpload) throws IOException {
        Map uploadResult = null;

        if (songUpload.getFile() != null && !songUpload.getFile().isEmpty()) {
            uploadResult = cloudinary.uploader().upload(songUpload.getFile().getBytes(),
                    ObjectUtils.asMap("resource_type", "auto", "folder", "/song"));
            songUpload.setPublicId((String) uploadResult.get("public_id"));
            Object version = uploadResult.get("version");

            logger.info("Upload source success: " + uploadResult);

            if (version instanceof Integer) {
                songUpload.setVersion(Long.valueOf(((Integer) version)));
            } else {
                songUpload.setVersion((Long) version);
            }

            songUpload.setSignature((String) uploadResult.get("signature"));
            songUpload.setFormat((String) uploadResult.get("format"));
            songUpload.setResourceType((String) uploadResult.get("resource_type"));
        }

        String songSrcUrl = songUpload.getUrl(cloudinary);
        song.setSrc(songSrcUrl);
        song.setSrcId(songUpload.getPublicId());

        return song;
    }

    public Song uploadSongThumbnail(Song song, @ModelAttribute SongThumbnailUpload songThumbnailUpload) throws IOException {
        Map uploadResult = null;

        if (songThumbnailUpload.getFile() != null && !songThumbnailUpload.getFile().isEmpty()) {
            uploadResult = cloudinary.uploader().upload(songThumbnailUpload.getFile().getBytes(),
                    ObjectUtils.asMap("resource_type", "auto", "folder", "/thumbnail"));
            songThumbnailUpload.setPublicId((String) uploadResult.get("public_id"));
            Object version = uploadResult.get("version");

            logger.info("Upload source success: " + uploadResult);

            if (version instanceof Integer) {
                songThumbnailUpload.setVersion(Long.valueOf(((Integer) version)));
            } else {
                songThumbnailUpload.setVersion((Long) version);
            }

            songThumbnailUpload.setSignature((String) uploadResult.get("signature"));
            songThumbnailUpload.setFormat((String) uploadResult.get("format"));
            songThumbnailUpload.setResourceType((String) uploadResult.get("resource_type"));
        }

        String songThumbUrl = songThumbnailUpload.getUrl(cloudinary);
        song.setThumbnail(songThumbUrl);
        song.setThumbnailId(songThumbnailUpload.getPublicId());

        return song;
    }

}

class SortSongByName implements Comparator<Song> {
    @Override
    public int compare(Song o1, Song o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
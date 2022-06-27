package com.training.MusicPlayer.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.training.MusicPlayer.dto.SongDto;
import com.training.MusicPlayer.models.*;
import com.training.MusicPlayer.repositories.SongRepository;
import java.io.IOException;
import com.training.MusicPlayer.utils.SongSourceUpload;
import com.training.MusicPlayer.utils.SongThumbnailUpload;
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
    @Autowired
    private GenreService genreService;
    @Autowired
    private AuthorService authorService;
    private final Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dungtd",
            "api_key", "553685374214836",
            "api_secret", "QLOlTiPPPESG9iyQhzG634GfhBQ"));
    private static final Logger logger = LoggerFactory.getLogger(SongService.class);
    public List<SongDto> findAllDto(Boolean shuffle) {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.ASC, "updateAt"));

        List<Song> songs = mongoTemplate.find(query, Song.class, "song");
        List<SongDto> songsDto = new ArrayList<>();
        for (Song s:
             songs) {
            SongDto songDto = new SongDto();
            songDto.clone(s);
            songDto.setGenre(genreService.getById(s.getGenre()).get());
            songDto.setAuthor(authorService.getById(s.getAuthor()).get());
            songsDto.add(songDto);
        }

        if (shuffle) {
            Collections.shuffle(songsDto);
            return songsDto;
        }

        return songsDto;
    }

    public List<Song> findAll() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.ASC, "updateAt"));


        return mongoTemplate.find(query, Song.class, "song");
    }

    public long count(String name, String author, String genre) {
        logger.info("Counting page with name: " + name + " and author:" + author + " and genre: " + genre);

        Query query = new Query();

        if (!name.equals("")) {
            query.addCriteria(Criteria.where("name").regex(name, "i"));
        }
        if (!author.equals("")) {
            query.addCriteria(Criteria.where("author").regex(author, "i"));
        }
        if (!genre.equals("")) {
            query.addCriteria(Criteria.where("genre").regex(genre, "i"));
        }

        return mongoTemplate.count(query, Song.class, "song");
    }

    public SongDto findById(String id) {
        SongDto songDto = new SongDto();
        Optional<Song> song = repository.findById(id);
        if (song.isPresent()) {
            songDto.clone(song.get());
            Optional<Genre> genre = genreService.getById(song.get().getGenre());
            Optional<Author> author = authorService.getById(song.get().getAuthor());

            if (genre.isPresent() && author.isPresent()) {
                songDto.setGenre(genre.get());
                songDto.setAuthor(author.get());
                return songDto;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public Boolean checkSong(Song song) {
        logger.info("Checking song: " + song);
        Query query = new Query();


        query.addCriteria(Criteria.where("name").regex(song.getName(), "i"));
        query.addCriteria(Criteria.where("author").regex(song.getAuthor(), "i"));

        List<Song> songs = mongoTemplate.find(query, Song.class, "song");

        for (Song s: songs
             ) {
            logger.info("Comparing with song: " + s);
            if (s.equals(song)) {
                return s.getId().equals(song.getId());
            }
        }
        return true;
    }

    public String save(Song song) {
        repository.save(song);
        return song.getName();
    }

    public SongPage getPage(String name, String author, String genre, int index, Integer limit, Pageable pageable) {
        logger.info("Getting page: " + index + " and limit: " + limit + " and name: " + name + " and author:" + author + " and genre: " + genre);

        Query query = new Query();
        query.with(pageable);

        if (!name.equals("")) {
            query.addCriteria(Criteria.where("name").regex(name, "i"));
        }
        if (!author.equals("")) {
            query.addCriteria(Criteria.where("author").regex(author, "i"));
        }
        if (!genre.equals("")) {
            query.addCriteria(Criteria.where("genre").regex(genre, "i"));
        }

        query.with(Sort.by(Sort.Direction.DESC, "updateAt"));

        List<Song> songs = mongoTemplate.find(query, Song.class, "song");

        logger.info("Songs in page: " + songs.size());
        List<SongDto> songsDto = new ArrayList<>();
        for (Song s:
                songs) {

            SongDto songDto = new SongDto();
            songDto.clone(s);

            songDto.setGenre(genreService.getById(s.getGenre()).get());
            songDto.setAuthor(authorService.getById(s.getAuthor()).get());

            songsDto.add(songDto);
        }

        return new SongPage(songsDto, pageable);
    }

    public Song editSong(Song song) {
        logger.info("Editing song: " + song.getName());
        Optional<Song> songToUpdate = repository.findById(song.getId());

        if (songToUpdate.isPresent()) {
            songToUpdate.get().updateData(song);
            songToUpdate.get().setUpdateAt(new Date());
            return songToUpdate.get();
        } else {
            return null;
        }
    }

    public String delete(Song song) throws IOException {
        logger.info("Deleting song: " + song.getName());
        Optional<Song> songToDelete = repository.findById(song.getId());

        if (songToDelete.isEmpty())
            return null;


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

    public List<SongDto> getRelatedSong(String songId) {
        logger.info("Getting next song for song: " + songId);
        List<SongDto> related = new ArrayList<>();
        Song song = mongoTemplate.findById(songId, Song.class, "song");
        if (song == null) {
            return null;
        }
        Query queryNext = new Query();
        queryNext.addCriteria(Criteria.where("updateAt").gt(song.getUpdateAt()));
        queryNext.with(Sort.by(Sort.Direction.ASC, "updateAt"));
        List<Song> nextSongs = mongoTemplate.find(queryNext, Song.class, "song");

        SongDto songDtoNext = new SongDto();


        logger.info("NextSong: " + nextSongs);

        if (nextSongs.size() > 0) {
            songDtoNext.clone(nextSongs.get(0));
            Optional<Genre> genre = genreService.getById(nextSongs.get(0).getGenre());
            Optional<Author> author = authorService.getById(nextSongs.get(0).getAuthor());
            songDtoNext.setGenre(genre.get());
            songDtoNext.setAuthor(author.get());
            related.add(songDtoNext);

            logger.info("List updated next 1: " + songDtoNext);
        } else {
            queryNext = new Query();
            queryNext.with(Sort.by(Sort.Direction.ASC, "updateAt"));
            Song s = mongoTemplate.find(queryNext, Song.class, "song").get(0);
            songDtoNext.clone(s);
            songDtoNext.setGenre(genreService.getById(s.getGenre()).get());
            songDtoNext.setAuthor(authorService.getById(s.getAuthor()).get());
            related.add(songDtoNext);

            logger.info("List updated next 2: " + songDtoNext);

        }

        SongDto songDtoPrevious = new SongDto();


        Query queryPrevious = new Query();
        queryPrevious.addCriteria(Criteria.where("updateAt").lt(song.getUpdateAt()));
        queryPrevious.with(Sort.by(Sort.Direction.DESC, "updateAt"));
        List<Song> previousSongs = mongoTemplate.find(queryPrevious, Song.class, "song");

        logger.info("Previous: " + previousSongs);

        if (previousSongs.size() > 0) {
            songDtoPrevious.clone(previousSongs.get(0));
            Optional<Genre> genre = genreService.getById(previousSongs.get(0).getGenre());
            Optional<Author> author = authorService.getById(previousSongs.get(0).getAuthor());

            songDtoPrevious.setAuthor(author.get());
            songDtoPrevious.setGenre(genre.get());

            related.add(songDtoPrevious);
            logger.info("List updated previous 1: " + songDtoPrevious);

        } else {
            queryPrevious = new Query();
            queryPrevious.with(Sort.by(Sort.Direction.DESC, "updateAt"));
            Song s = mongoTemplate.find(queryPrevious, Song.class, "song").get(0);
            songDtoPrevious.clone(s);
            songDtoPrevious.setGenre(genreService.getById(s.getGenre()).get());
            songDtoNext.setAuthor(authorService.getById(s.getAuthor()).get());

            related.add(songDtoPrevious);

            logger.info("List updated previous 2: " + songDtoPrevious);
        }

        logger.info("Result:" + related.toString());
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
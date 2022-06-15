package com.training.MusicPlayer.models;
import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;

public class CloudinaryInstance {
    public Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dungtd",
            "api_key", "553685374214836",
            "api_secret", "QLOlTiPPPESG9iyQhzG634GfhBQ"));
}

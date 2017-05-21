package me.akhilnarang.eztvapp;

import java.io.Serializable;

/**
 * Created by men_in_black007 on 23/5/17.
 */

public class EZTVModel implements Serializable {

    private String filename;
    private String episodeUrl;
    private String magnetUrl;
    private String imageUrl;

    public EZTVModel(String filename, String episodeUrl, String magnetUrl) {
        this.filename = filename;
        this.episodeUrl = episodeUrl;
        this.magnetUrl = magnetUrl;
    }

    public EZTVModel(String filename, String episodeUrl, String magnetUrl, String imageUrl) {
        this.filename = filename;
        this.episodeUrl = episodeUrl;
        this.magnetUrl = magnetUrl;
        this.imageUrl = imageUrl;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getEpisodeUrl() {
        return episodeUrl;
    }

    public void setEpisodeUrl(String episodeUrl) {
        this.episodeUrl = episodeUrl;
    }

    public String getMagnetUrl() {
        return magnetUrl;
    }

    public void setMagnetUrl(String magnetUrl) {
        this.magnetUrl = magnetUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

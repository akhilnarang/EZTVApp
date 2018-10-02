package me.akhilnarang.eztvapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Torrent implements Serializable {
    @SerializedName("filename")
    @Expose
    private String filename;
    @SerializedName("episode_url")
    @Expose
    private String episodeUrl;
    @SerializedName("torrent_url")
    @Expose
    private String torrentUrl;
    @SerializedName("magnet_url")
    @Expose
    private String magnetUrl;
    @SerializedName("small_screenshot")
    @Expose
    private String smallScreenshot;
    @SerializedName("large_screenshot")
    @Expose
    private String largeScreenshot;


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

    public String getTorrentUrl() {
        return torrentUrl;
    }

    public void setTorrentUrl(String torrentUrl) {
        this.torrentUrl = torrentUrl;
    }

    public String getMagnetUrl() {
        return magnetUrl;
    }

    public void setMagnetUrl(String magnetUrl) {
        this.magnetUrl = magnetUrl;
    }

    public String getSmallScreenshot() {
        return smallScreenshot;
    }

    public void setSmallScreenshot(String smallScreenshot) {
        this.smallScreenshot = smallScreenshot;
    }

    public String getLargeScreenshot() {
        return largeScreenshot;
    }

    public void setLargeScreenshot(String largeScreenshot) {
        this.largeScreenshot = largeScreenshot;
    }

}



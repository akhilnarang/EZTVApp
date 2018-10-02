package me.akhilnarang.eztvapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by men_in_black007 on 23/5/17.
 */

public class EZTVModel{

    @SerializedName("torrents_count")
    @Expose
    private Integer torrentsCount;
    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("torrents")
    @Expose
    private List<Torrent> torrents = null;

    public Integer getTorrentsCount() {
        return torrentsCount;
    }

    public void setTorrentsCount(Integer torrentsCount) {
        this.torrentsCount = torrentsCount;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Torrent> getTorrents() {
        return torrents;
    }

    public void setTorrents(List<Torrent> torrents) {
        this.torrents = torrents;
    }

}


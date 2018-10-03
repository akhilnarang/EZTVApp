package me.akhilnarang.eztvapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EZTVModel {

    @SerializedName("torrents_count")
    @Expose
    var torrentsCount: Int? = null
    @SerializedName("limit")
    @Expose
    var limit: Int? = null
    @SerializedName("page")
    @Expose
    var page: Int? = null
    @SerializedName("torrents")
    @Expose
    var torrents: List<Torrent>? = null
}

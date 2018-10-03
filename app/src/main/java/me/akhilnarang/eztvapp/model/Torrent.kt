package me.akhilnarang.eztvapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Torrent : Serializable {
    @SerializedName("filename")
    @Expose
    var filename: String? = null
    @SerializedName("episode_url")
    @Expose
    var episodeUrl: String? = null
    @SerializedName("torrent_url")
    @Expose
    var torrentUrl: String? = null
    @SerializedName("magnet_url")
    @Expose
    var magnetUrl: String? = null
    @SerializedName("small_screenshot")
    @Expose
    var smallScreenshot: String? = null
    @SerializedName("large_screenshot")
    @Expose
    var largeScreenshot: String? = null

}
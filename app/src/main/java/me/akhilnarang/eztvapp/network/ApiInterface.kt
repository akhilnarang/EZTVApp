package me.akhilnarang.eztvapp.network

import me.akhilnarang.eztvapp.model.EZTVModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("get-torrents")
    fun getTorrents(@Query("limit") limit: Int, @Query("page") page: Int): Call<EZTVModel>
}

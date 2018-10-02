package me.akhilnarang.eztvapp.Network;

import me.akhilnarang.eztvapp.Model.EZTVModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("get-torrents")
    Call<EZTVModel> getTorrents(@Query("limit") int limit, @Query("page") int page);
}

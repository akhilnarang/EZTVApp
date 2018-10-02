package me.akhilnarang.eztvapp.Network;

import me.akhilnarang.eztvapp.Model.EZTVModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("get-torrents?limit=10&page=1")
    Call<EZTVModel> getTorrents();
}

package com.mobile.fatah.learnrequestapi.network;

import com.mobile.fatah.learnrequestapi.model.ResponseBerita;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    //TODO cara request
    @GET("tampil_berita.php")
    Call<ResponseBerita> getDataBerita();
}

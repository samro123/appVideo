package com.thellog.laravelapiandroid.Service;

import com.thellog.laravelapiandroid.Model.Video_Model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {
    @GET("videos")
    Call<Video_Model> getVideo();
}

package com.example.android.movieapp;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @GET("3/movie/popular?api_key=ad7b6dbe2cd8c3b637d59a7ac1677960&language=en")
    Call<JsonObject> getMovies(@Query("page") int page);

}

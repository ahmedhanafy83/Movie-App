package com.example.android.movieapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TheMovie {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("vote_average")
    @Expose
    public double voteAverage;
    @SerializedName("title")
    @Expose
    public String name;
    @SerializedName("poster_path")
    @Expose
    public String posterPath;

    public TheMovie(int id, double voteAverage, String name, String posterPath) {
        this.id = id;
        this.voteAverage = voteAverage;
        this.name = name;
        this.posterPath = posterPath;
    }

    public int getId() {
        return id;
    }


    public double getVoteAverage() {
        return voteAverage;
    }


    public String getName() {
        return name;
    }


    public String getPosterPath() {
        return posterPath;
    }

}



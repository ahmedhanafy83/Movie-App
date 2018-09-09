package com.example.android.movieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<JsonObject> {
    private RecyclerView recyclerMovies;
    private ArrayList<TheMovie> movies;
    private MovieAdapter adapter;
    private ProgressBar progressBar;
    private ProgressBar progressBar2;
    private boolean loading = true;
    private LinearLayoutManager mLayoutManager;
    private int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerMovies = findViewById(R.id.recycle_movie);
        progressBar = findViewById(R.id.loading);
        progressBar2 = findViewById(R.id.loading_bar);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerMovies.setLayoutManager(mLayoutManager);
        movies = new ArrayList<>();
        adapter = new MovieAdapter(this, movies);
        recyclerMovies.setAdapter(adapter);
        recyclerMovies.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    int visibleItemCount = mLayoutManager.getChildCount();
                    int totalItemCount = mLayoutManager.getItemCount();
                    int pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            Log.v("...", "Last Item Wow !");
                            i++;
                            progressBar2.setVisibility(View.VISIBLE);
                            getMovies();
                        }
                    }
                }
            }
        });

        getMovies();
    }

    private void getMovies() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);
        api.getMovies(i).enqueue(this);
    }


    @Override
    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
        loading = true;
        progressBar.setVisibility(View.GONE);
        progressBar2.setVisibility(View.GONE);
        if (response.body() != null) {
            if (movies == null)
                movies = new ArrayList<>();
            movies.addAll(response.body().movies);
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "No Movies Founded", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<JsonObject> call, Throwable t) {
        loading = true;
        progressBar.setVisibility(View.GONE);
        progressBar2.setVisibility(View.GONE);
        t.printStackTrace();
        Toast.makeText(this, "Error in Connection", Toast.LENGTH_SHORT).show();

    }

}

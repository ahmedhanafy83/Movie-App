package com.example.android.movieapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.Holder> {

    private Context context ;
    private ArrayList<TheMovie> movies;

    public MovieAdapter(Context context, ArrayList<TheMovie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       Log.d("test","In On Create View Holder");
        return new Holder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        Log.d("test", "In On Bind");
        holder.theMovie = movies.get(position);
        holder.tvMovieName.setText(movies.get(position).getName());
        holder.ratingMovie.setRating((float) movies.get(position).getVoteAverage());
        GlideApp.with(context)
                .load("http://image.tmdb.org/t/p/w500/" + movies.get(position).getPosterPath())
                .into(holder.ivMovie);



    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        ImageView ivMovie;
        TextView tvMovieName;
        RatingBar ratingMovie;
        public TheMovie theMovie;

        public Holder(View view) {
            super(view);
            ivMovie = view.findViewById(R.id.iv_movie);
            tvMovieName = view.findViewById(R.id.tv_movie_name);
            ratingMovie = view.findViewById(R.id.rating_movie);

        }
    }
}

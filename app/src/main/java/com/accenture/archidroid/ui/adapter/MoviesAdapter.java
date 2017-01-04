package com.accenture.archidroid.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.accenture.archidroid.R;
import com.accenture.archidroid.model.Movie;

import java.util.List;

/**
 * Created by ugurcan.yildirim on 28.12.2016.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Movie> movieList;

    public MoviesAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        holder.title.setText(movie.title);
        holder.year.setText(movie.year);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            year = (TextView) view.findViewById(R.id.year);
        }
    }

}
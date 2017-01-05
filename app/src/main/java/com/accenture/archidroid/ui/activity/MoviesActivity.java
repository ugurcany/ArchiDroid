package com.accenture.archidroid.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.accenture.archidroid.App;
import com.accenture.archidroid.R;
import com.accenture.archidroid.logic.activity.ActivityComponent;
import com.accenture.archidroid.logic.activity.DaggerMoviesActivityComponent;
import com.accenture.archidroid.logic.activity.MoviesActivityComponent;
import com.accenture.archidroid.model.data.BaseData;
import com.accenture.archidroid.model.data.Movie;
import com.accenture.archidroid.model.data.Movies;
import com.accenture.archidroid.ui.adapter.MoviesAdapter;
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
public class MoviesActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private RecyclerTouchListener recyclerTouchListener;

    private ArrayList<Movie> movieList;
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);

        dataKey = "lord";

        movieList = new ArrayList<>();
        moviesAdapter = new MoviesAdapter(movieList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(moviesAdapter);

        recyclerTouchListener = new RecyclerTouchListener(this, recyclerView);
        recyclerTouchListener.setClickable(new RecyclerTouchListener.OnRowClickListener() {
            @Override
            public void onRowClicked(int position) {
                Intent intent = new Intent(MoviesActivity.this, MovieDetailActivity.class);
                intent.putExtra("imdbId", movieList.get(position).imdbId);
                startActivity(intent);
            }
            @Override
            public void onIndependentViewClicked(int independentViewID, int position) {
            }
        });

    }

    @Override
    protected ActivityComponent init() {
        MoviesActivityComponent activityComponent = DaggerMoviesActivityComponent.builder()
                .restComponent(App.restComponent())
                .build();
        activityComponent.inject(this);

        return activityComponent;
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.addOnItemTouchListener(recyclerTouchListener);
    }

    @Override
    protected void onPause() {
        recyclerView.removeOnItemTouchListener(recyclerTouchListener);
        super.onPause();
    }

    @Override
    public void loadData() {
        activityComponent.executor().execute(dataKey, dataKey, "json");
    }

    @Override
    public <D extends BaseData> void onDataArrived(D data){
        Movies movies = (Movies) data;
        //RESPONSE IS TRUE
        if (Boolean.parseBoolean(movies.response)) {
            movieList.clear();
            for (Movie movie : movies.movieList) {
                movieList.add(movie);
            }
            moviesAdapter.notifyDataSetChanged();
        }
    }

}

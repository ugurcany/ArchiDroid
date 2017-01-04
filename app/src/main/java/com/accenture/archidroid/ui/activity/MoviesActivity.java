package com.accenture.archidroid.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.accenture.archidroid.App;
import com.accenture.archidroid.R;
import com.accenture.archidroid.model.event.MoviesEvent;
import com.accenture.archidroid.logic.activity.DaggerMoviesActivityComponent;
import com.accenture.archidroid.logic.activity.MoviesActivityComponent;
import com.accenture.archidroid.model.data.Movie;
import com.accenture.archidroid.ui.adapter.MoviesAdapter;
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
public class MoviesActivity extends BaseActivity {

    private final String TAG = getClass().getSimpleName();

    private MoviesActivityComponent activityComponent;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private RecyclerTouchListener recyclerTouchListener;

    private ArrayList<Movie> movieList;
    private MoviesAdapter moviesAdapter;

    private String dataKey = "lord";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent = DaggerMoviesActivityComponent.builder()
                .restComponent(App.restComponent())
                .build();
        activityComponent.inject(this);

        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);

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
    protected void onDestroy() {
        activityComponent = null;
        super.onDestroy();
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

    @Subscribe
    public void getMoviesResponse(MoviesEvent event){
        if(event.success) {
            //RESPONSE BELONGS TO OUR SEARCH KEY & IS TRUE
            if (dataKey.equals(event.key) && Boolean.parseBoolean(event.data.response)) {
                movieList.clear();
                for (Movie movie : event.data.movieList) {
                    movieList.add(movie);
                }
                moviesAdapter.notifyDataSetChanged();
            }
        }
        else{
            Log.d(TAG, "Err!");
        }
    }

}

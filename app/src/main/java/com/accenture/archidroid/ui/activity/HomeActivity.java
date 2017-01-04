package com.accenture.archidroid.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.accenture.archidroid.App;
import com.accenture.archidroid.R;
import com.accenture.archidroid.event.MoviesEvent;
import com.accenture.archidroid.logic.executor.Executor;
import com.accenture.archidroid.logic.executor.ExecutorModule;
import com.accenture.archidroid.model.Movie;
import com.accenture.archidroid.ui.adapter.MoviesAdapter;
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
public class HomeActivity extends BaseActivity {

    private final String TAG = getClass().getSimpleName();

    @Inject
    @Named(ExecutorModule.MOVIES)
    Executor executor;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private RecyclerTouchListener recyclerTouchListener;

    private ArrayList<Movie> movieList;
    private MoviesAdapter moviesAdapter;

    private String dataKey = "lord";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.injector().inject(this);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        movieList = new ArrayList<>();
        moviesAdapter = new MoviesAdapter(movieList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(moviesAdapter);

        recyclerTouchListener = new RecyclerTouchListener(this, recyclerView);
        recyclerTouchListener.setClickable(new RecyclerTouchListener.OnRowClickListener() {
            @Override
            public void onRowClicked(int position) {
                Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
                intent.putExtra("imdbId", movieList.get(position).imdbId);
                startActivity(intent);
            }
            @Override
            public void onIndependentViewClicked(int independentViewID, int position) {
            }
        });

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
        executor.execute(dataKey, dataKey, "json");
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

package com.accenture.archidroid.ui;

import android.os.Bundle;
import android.util.Log;

import com.accenture.archidroid.App;
import com.accenture.archidroid.R;
import com.accenture.archidroid.handler.Communicator;
import com.accenture.archidroid.model.Movie;
import com.accenture.archidroid.model.Movies;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
public class MainActivity extends BaseActivity {

    private final String TAG = getClass().getSimpleName();

    @Inject
    Communicator communicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.injector().mainComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @Override
    public void loadData() {
        communicator.getMovies("lord");
    }

    @Subscribe
    public void getMoviesResponse(Movies movies){
        Log.d(TAG, "New data posted!");
        for(Movie movie : movies.movieList){
            Log.d(TAG, movie.toString());
        }
    }

}

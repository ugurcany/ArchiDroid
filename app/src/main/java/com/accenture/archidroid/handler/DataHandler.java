package com.accenture.archidroid.handler;

import com.accenture.archidroid.model.Movies;
import com.anupcowkur.reservoir.Reservoir;
import com.anupcowkur.reservoir.ReservoirGetCallback;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
public class DataHandler {

    private final String KEY_MOVIES = "movies";

    private Movies movies = null;

    //MOVIES
    public Movies getMovies(){
        try {
            if (Reservoir.contains(KEY_MOVIES)) {
                Reservoir.getAsync(KEY_MOVIES, Movies.class, new ReservoirGetCallback<Movies>() {
                    @Override
                    public void onSuccess(Movies movies) {
                        setMovies(movies);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        //error
                    }
                });
            }
        }catch (Exception ignored){}

        return movies;
    }

    public void setMovies(Movies movies){
        //IF SAME DATA IS ALREADY SET -OR- NEW DATA IS NULL --> EXIT
        if((this.movies != null && movies != null && this.movies.equals(movies)) || (movies == null)){
            return;
        }

        this.movies = movies;
        Reservoir.putAsync(KEY_MOVIES, movies, null);

        postMovies();
    }

    public void postMovies(){
        if(movies != null){
            EventBus.getDefault().post(movies);
        }
    }

}

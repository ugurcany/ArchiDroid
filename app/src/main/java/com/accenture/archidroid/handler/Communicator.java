package com.accenture.archidroid.handler;

import com.accenture.archidroid.App;
import com.accenture.archidroid.dagger.main.RestModule;
import com.accenture.archidroid.model.Movies;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
public class Communicator {

    @Inject
    DataHandler dataHandler;

    private RestModule.Api restApi;

    public Communicator(RestModule.Api restApi){
        App.injector().dataComponent().inject(this);

        this.restApi = restApi;
    }

    public void getMovies(String searchKey){
        if(dataHandler.getMovies() != null){
            dataHandler.postMovies();
        }

        restApi.getMovies(searchKey, "json").enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if(response.isSuccessful()) {
                    Movies movies = response.body();
                    dataHandler.setMovies(movies);
                }
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });
    }

}

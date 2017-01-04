package com.accenture.archidroid.logic.executor;

import com.accenture.archidroid.model.Movie;
import com.accenture.archidroid.model.Movies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ugurcan.yildirim on 4.01.2017.
 */
public interface RestApi {

    @GET("/")
    public Call<Movies> getMovies(
            @Query("s") String searchKey,
            @Query("r") String responseType
    );

    @GET("/")
    public Call<Movie> getMovieDetail(
            @Query("i") String imdbId,
            @Query("plot") String plotLength,
            @Query("r") String responseType
    );

}
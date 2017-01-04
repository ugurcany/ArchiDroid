package com.accenture.archidroid.logic.executor;

import com.accenture.archidroid.logic.data.DataHandler;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
@Module
public class ExecutorModule {

    public static final String MOVIES = "MOVIES";
    public static final String MOVIE_DETAIL = "MOVIE_DETAIL";

    @Provides
    @ExeScope
    @Named(ExecutorModule.MOVIES)
    Executor providesEmovies(RestApi restApi, @Named(ExecutorModule.MOVIES) DataHandler dataHandler) {
        return new Executor(restApi, dataHandler, "getMovies");
    }

    @Provides
    @ExeScope
    @Named(ExecutorModule.MOVIE_DETAIL)
    Executor providesEmovieDetail(RestApi restApi, @Named(ExecutorModule.MOVIE_DETAIL) DataHandler dataHandler) {
        return new Executor(restApi, dataHandler, "getMovieDetail");
    }

}

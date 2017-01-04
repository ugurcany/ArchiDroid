package com.accenture.archidroid.logic.data;

import com.accenture.archidroid.event.MovieDetailEvent;
import com.accenture.archidroid.event.MoviesEvent;
import com.accenture.archidroid.logic.executor.ExecutorModule;
import com.accenture.archidroid.model.Movie;
import com.accenture.archidroid.model.Movies;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
@Module
public class DataModule {

    @Provides
    @DataScope
    @Named(ExecutorModule.MOVIES)
    DataHandler providesDHmovies() {
        return new DataHandler<Movies, MoviesEvent>(Movies.class, MoviesEvent.class);
    }

    @Provides
    @DataScope
    @Named(ExecutorModule.MOVIE_DETAIL)
    DataHandler providesDHmovieDetail() {
        return new DataHandler<Movie, MovieDetailEvent>(Movie.class, MovieDetailEvent.class);
    }

}

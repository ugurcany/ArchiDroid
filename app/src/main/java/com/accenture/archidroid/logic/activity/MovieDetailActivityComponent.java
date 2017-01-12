package com.accenture.archidroid.logic.activity;

import com.accenture.archidroid.Scope;
import com.accenture.archidroid.model.event.MovieDetailEvent;
import com.accenture.archidroid.logic.DataHandler;
import com.accenture.archidroid.logic.Executor;
import com.accenture.archidroid.logic.rest.RestApi;
import com.accenture.archidroid.logic.rest.RestComponent;
import com.accenture.archidroid.model.data.Movie;
import com.accenture.archidroid.ui.activity.MovieDetailActivity;

import javax.inject.Named;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
@Scope.ActivityScope
@Component(dependencies={RestComponent.class}, modules={MovieDetailActivityComponent.ActivityModule.class})
public interface MovieDetailActivityComponent {

    String GET_MOVIE_DETAIL = "getMovieDetail"; //SERVICE NAME

    void inject(MovieDetailActivity activity);

    @Named(GET_MOVIE_DETAIL)
    Executor getMovieDetail();

    @Module
    class ActivityModule {

        @Provides @Named(GET_MOVIE_DETAIL)
        @Scope.ActivityScope
        Executor providesGetMovieDetail(RestApi restApi, DataHandler dataHandler) {
            return new Executor<Movie>(restApi, dataHandler, GET_MOVIE_DETAIL);
        }

        @Provides
        @Scope.ActivityScope
        DataHandler providesDataHandler() {
            return new DataHandler<Movie, MovieDetailEvent>(Movie.class, MovieDetailEvent.class);
        }

    }

}

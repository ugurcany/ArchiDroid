package com.accenture.archidroid.logic.activity;

import com.accenture.archidroid.Scope;
import com.accenture.archidroid.model.event.MoviesEvent;
import com.accenture.archidroid.logic.DataHandler;
import com.accenture.archidroid.logic.Executor;
import com.accenture.archidroid.logic.rest.RestApi;
import com.accenture.archidroid.logic.rest.RestComponent;
import com.accenture.archidroid.model.data.Movies;
import com.accenture.archidroid.ui.activity.MoviesActivity;

import javax.inject.Named;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
@Scope.ActivityScope
@Component(dependencies={RestComponent.class}, modules={MoviesActivityComponent.ActivityModule.class})
public interface MoviesActivityComponent {

    String GET_MOVIES = "getMovies"; //SERVICE NAME

    void inject(MoviesActivity activity);

    @Named(GET_MOVIES)
    Executor getMovies();

    @Module
    class ActivityModule {

        @Provides @Named(GET_MOVIES)
        @Scope.ActivityScope
        Executor providesGetMovies(RestApi restApi, DataHandler dataHandler) {
            return new Executor<Movies>(restApi, dataHandler, GET_MOVIES);
        }

        @Provides
        @Scope.ActivityScope
        DataHandler providesDataHandler() {
            return new DataHandler<Movies, MoviesEvent>(Movies.class, MoviesEvent.class);
        }

    }

}

package com.accenture.archidroid.logic.activity;

import com.accenture.archidroid.model.event.MoviesEvent;
import com.accenture.archidroid.logic.scope.ActivityScope;
import com.accenture.archidroid.logic.DataHandler;
import com.accenture.archidroid.logic.Executor;
import com.accenture.archidroid.logic.rest.RestApi;
import com.accenture.archidroid.logic.rest.RestComponent;
import com.accenture.archidroid.model.data.Movies;
import com.accenture.archidroid.ui.activity.MoviesActivity;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
@ActivityScope
@Component(dependencies={RestComponent.class}, modules={MoviesActivityComponent.ActivityModule.class})
public interface MoviesActivityComponent {

    void inject(MoviesActivity activity);

    Executor executor();

    @Module
    class ActivityModule {

        @Provides
        @ActivityScope
        Executor providesExecutor(RestApi restApi, DataHandler dataHandler) {
            return new Executor<Movies>(restApi, dataHandler, "getMovies");
        }

        @Provides
        @ActivityScope
        DataHandler providesDataHandler() {
            return new DataHandler<Movies, MoviesEvent>(Movies.class, MoviesEvent.class);
        }

    }

}

package com.accenture.archidroid.logic.activity;

import com.accenture.archidroid.model.event.MovieDetailEvent;
import com.accenture.archidroid.logic.scope.ActivityScope;
import com.accenture.archidroid.logic.DataHandler;
import com.accenture.archidroid.logic.Executor;
import com.accenture.archidroid.logic.rest.RestApi;
import com.accenture.archidroid.logic.rest.RestComponent;
import com.accenture.archidroid.model.data.Movie;
import com.accenture.archidroid.ui.activity.MovieDetailActivity;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
@ActivityScope
@Component(dependencies={RestComponent.class}, modules={MovieDetailActivityComponent.ActivityModule.class})
public interface MovieDetailActivityComponent {

    void inject(MovieDetailActivity activity);

    Executor executor();

    @Module
    class ActivityModule {

        @Provides
        @ActivityScope
        Executor providesExecutor(RestApi restApi, DataHandler dataHandler) {
            return new Executor<Movie>(restApi, dataHandler, "getMovieDetail");
        }

        @Provides
        @ActivityScope
        DataHandler providesDataHandler() {
            return new DataHandler<Movie, MovieDetailEvent>(Movie.class, MovieDetailEvent.class);
        }

    }

}

package com.accenture.archidroid.logic.data;

import com.accenture.archidroid.logic.executor.ExecutorModule;

import javax.inject.Named;

import dagger.Component;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
@DataScope
@Component(modules={DataModule.class})
public interface DataComponent {

    @Named(ExecutorModule.MOVIES)
    DataHandler DHmovies();

    @Named(ExecutorModule.MOVIE_DETAIL)
    DataHandler DHmovieDetail();

}

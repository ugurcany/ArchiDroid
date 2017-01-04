package com.accenture.archidroid.logic.executor;

import com.accenture.archidroid.logic.data.DataComponent;

import javax.inject.Named;

import dagger.Component;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
@ExeScope
@Component(dependencies={DataComponent.class}, modules={ExecutorModule.class, RestModule.class})
public interface ExecutorComponent {

    @Named(ExecutorModule.MOVIES)
    Executor Emovies();

    @Named(ExecutorModule.MOVIE_DETAIL)
    Executor EmovieDetail();

}

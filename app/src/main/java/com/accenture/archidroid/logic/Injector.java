package com.accenture.archidroid.logic;

import com.accenture.archidroid.logic.executor.ExecutorComponent;
import com.accenture.archidroid.ui.activity.DetailActivity;
import com.accenture.archidroid.ui.activity.HomeActivity;

import dagger.Component;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
@AppScope
@Component(dependencies={ExecutorComponent.class})
public interface Injector {

    void inject(HomeActivity activity);
    void inject(DetailActivity activity);

}

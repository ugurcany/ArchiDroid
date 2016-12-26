package com.accenture.archidroid.dagger.main;

import com.accenture.archidroid.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
@Singleton
@Component(modules={MainModule.class, RestModule.class})
public interface MainComponent {

    void inject(MainActivity activity);

}

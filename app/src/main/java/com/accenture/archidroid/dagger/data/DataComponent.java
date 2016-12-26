package com.accenture.archidroid.dagger.data;

import com.accenture.archidroid.handler.Communicator;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
@Singleton
@Component(modules={DataModule.class})
public interface DataComponent {

    void inject(Communicator communicator);

}

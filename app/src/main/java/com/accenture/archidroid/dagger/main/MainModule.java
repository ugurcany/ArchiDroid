package com.accenture.archidroid.dagger.main;

import com.accenture.archidroid.handler.Communicator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
@Module
public class MainModule {

    @Provides
    @Singleton
    Communicator providesCommunicator(RestModule.Api restApi) {
        return new Communicator(restApi);
    }

}

package com.accenture.archidroid.dagger.data;

import com.accenture.archidroid.handler.DataHandler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
@Module
public class DataModule {

    @Provides
    @Singleton
    DataHandler providesDataHandler() {
        return new DataHandler();
    }

}

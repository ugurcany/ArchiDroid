package com.accenture.archidroid;

import android.app.Application;

import com.accenture.archidroid.logic.rest.DaggerRestComponent;
import com.accenture.archidroid.logic.rest.RestComponent;
import com.accenture.archidroid.logic.rest.RestModule;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
public class App extends Application {

    private static RestComponent restComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        restComponent = DaggerRestComponent.builder()
                .restModule(new RestModule())
                .build();
    }

    public static RestComponent restComponent(){
        return restComponent;
    }

}

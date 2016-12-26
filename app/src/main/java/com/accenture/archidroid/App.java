package com.accenture.archidroid;

import android.app.Application;

import com.accenture.archidroid.dagger.Injector;
import com.anupcowkur.reservoir.Reservoir;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
public class App extends Application {

    private static Injector injector;

    @Override
    public void onCreate() {
        super.onCreate();

        try {
            Reservoir.init(this, 10 * 1024); //in bytes
        } catch (Exception e) {
            //failure
        }

        injector = new Injector(this);
    }

    public static Injector injector(){
        return injector;
    }

}

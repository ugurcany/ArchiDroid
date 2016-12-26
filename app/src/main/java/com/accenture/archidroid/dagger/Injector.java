package com.accenture.archidroid.dagger;

import android.app.Application;

import com.accenture.archidroid.dagger.data.DaggerDataComponent;
import com.accenture.archidroid.dagger.data.DataComponent;
import com.accenture.archidroid.dagger.data.DataModule;
import com.accenture.archidroid.dagger.main.DaggerMainComponent;
import com.accenture.archidroid.dagger.main.MainComponent;
import com.accenture.archidroid.dagger.main.MainModule;
import com.accenture.archidroid.dagger.main.RestModule;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
public class Injector {

    private MainComponent mainComponent;
    private DataComponent dataComponent;

    public Injector(Application application){
        mainComponent = DaggerMainComponent.builder()
                .mainModule(new MainModule())
                .restModule(new RestModule())
                .build();

        dataComponent = DaggerDataComponent.builder()
                .dataModule(new DataModule())
                .build();
    }

    public MainComponent mainComponent(){
        return mainComponent;
    }

    public DataComponent dataComponent(){
        return dataComponent;
    }

}

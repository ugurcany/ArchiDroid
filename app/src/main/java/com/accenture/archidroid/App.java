package com.accenture.archidroid;

import android.app.Application;

import com.accenture.archidroid.logic.DaggerInjector;
import com.accenture.archidroid.logic.Injector;
import com.accenture.archidroid.logic.data.DaggerDataComponent;
import com.accenture.archidroid.logic.data.DataComponent;
import com.accenture.archidroid.logic.data.DataModule;
import com.accenture.archidroid.logic.executor.DaggerExecutorComponent;
import com.accenture.archidroid.logic.executor.ExecutorComponent;
import com.accenture.archidroid.logic.executor.ExecutorModule;
import com.accenture.archidroid.logic.executor.RestModule;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
public class App extends Application {

    private static Injector injector;

    @Override
    public void onCreate() {
        super.onCreate();

        //DAGGER
        DataComponent dataComponent = DaggerDataComponent.builder()
                .dataModule(new DataModule())
                .build();

        ExecutorComponent executorComponent = DaggerExecutorComponent.builder()
                .dataComponent(dataComponent)
                .executorModule(new ExecutorModule())
                .restModule(new RestModule())
                .build();

        injector = DaggerInjector.builder()
                .executorComponent(executorComponent)
                .build();
    }

    public static Injector injector(){
        return injector;
    }

}

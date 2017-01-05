package com.accenture.archidroid.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.accenture.archidroid.logic.activity.ActivityComponent;
import com.accenture.archidroid.model.data.BaseData;
import com.accenture.archidroid.model.event.BaseEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected ActivityComponent activityComponent;
    protected String dataKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent = init();
    }

    @Override
    protected void onDestroy() {
        activityComponent = null;
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        loadData();
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public <E extends BaseEvent> void handleDataResponse(E event){
        if(event.success) {
            if(dataKey.equals(event.key)) { //RESPONSE BELONGS TO OUR KEY
                onDataArrived(event.data);
            }
            //ELSE IGNORE
        }
        else{
            //SHOW ERR, ETC.
        }
    }

    //TO BE IMPLEMENTED BY CHILD CLASSES
    protected abstract ActivityComponent init();
    protected abstract void loadData();
    protected abstract <D extends BaseData> void onDataArrived(D data);

}

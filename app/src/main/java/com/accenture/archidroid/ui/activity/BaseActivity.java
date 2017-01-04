package com.accenture.archidroid.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.accenture.archidroid.logic.activity.ActivityComponent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected ActivityComponent activityComponent;

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

    //TO BE IMPLEMENTED BY CHILD CLASSES
    protected abstract ActivityComponent init();
    protected abstract void loadData();

}

package com.accenture.archidroid.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.accenture.archidroid.App;
import com.accenture.archidroid.R;
import com.accenture.archidroid.logic.activity.ActivityComponent;
import com.accenture.archidroid.logic.activity.DaggerMovieDetailActivityComponent;
import com.accenture.archidroid.logic.activity.MovieDetailActivityComponent;
import com.accenture.archidroid.model.event.MovieDetailEvent;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
public class MovieDetailActivity extends BaseActivity {

    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.year)
    TextView year;

    @BindView(R.id.plot)
    TextView plot;

    private String dataKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviedetail);
        ButterKnife.bind(this);

        dataKey = getIntent().getStringExtra("imdbId");

    }

    @Override
    protected ActivityComponent init() {
        MovieDetailActivityComponent activityComponent = DaggerMovieDetailActivityComponent.builder()
                .restComponent(App.restComponent())
                .build();
        activityComponent.inject(this);

        return activityComponent;
    }

    @Override
    public void loadData() {
        activityComponent.executor().execute(dataKey, dataKey, "short", "json");
    }

    @Subscribe
    public void getMovieDetailsResponse(MovieDetailEvent event){
        if(event.success) {
            //RESPONSE BELONGS TO OUR SEARCH KEY & IS TRUE
            if (dataKey.equals(event.key) && Boolean.parseBoolean(event.data.response)) {
                title.setText(event.data.title);
                year.setText(event.data.year);
                plot.setText(event.data.plot);
            }
        }
        else{
            Log.d(TAG, "Err!");
        }
    }

}

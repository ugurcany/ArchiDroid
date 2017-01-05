package com.accenture.archidroid.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.accenture.archidroid.App;
import com.accenture.archidroid.R;
import com.accenture.archidroid.logic.activity.ActivityComponent;
import com.accenture.archidroid.logic.activity.DaggerMovieDetailActivityComponent;
import com.accenture.archidroid.logic.activity.MovieDetailActivityComponent;
import com.accenture.archidroid.model.data.BaseData;
import com.accenture.archidroid.model.data.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
public class MovieDetailActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.year)
    TextView year;

    @BindView(R.id.plot)
    TextView plot;

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

    @Override
    public <D extends BaseData> void onDataArrived(D data){
        Movie movie = (Movie) data;
        //RESPONSE IS TRUE
        if (Boolean.parseBoolean(movie.response)) {
            title.setText(movie.title);
            year.setText(movie.year);
            plot.setText(movie.plot);
        }
    }

}

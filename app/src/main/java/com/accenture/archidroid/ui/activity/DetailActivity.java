package com.accenture.archidroid.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.accenture.archidroid.App;
import com.accenture.archidroid.R;
import com.accenture.archidroid.event.MovieDetailEvent;
import com.accenture.archidroid.logic.executor.Executor;
import com.accenture.archidroid.logic.executor.ExecutorModule;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
public class DetailActivity extends BaseActivity {

    private final String TAG = getClass().getSimpleName();

    @Inject
    @Named(ExecutorModule.MOVIE_DETAIL)
    Executor executor;

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
        App.injector().inject(this);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        dataKey = getIntent().getStringExtra("imdbId");

    }

    @Override
    public void loadData() {
        executor.execute(dataKey, dataKey, "short", "json");
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

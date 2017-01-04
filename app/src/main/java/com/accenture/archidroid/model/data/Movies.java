package com.accenture.archidroid.model.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
public class Movies extends BaseData implements Serializable {

    @SerializedName("Search")
    public ArrayList<Movie> movieList;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Movies && ((Movies)obj).movieList != null && this.movieList != null) {
            return ((Movies)obj).movieList.equals(this.movieList);
        }
        return false;
    }

}

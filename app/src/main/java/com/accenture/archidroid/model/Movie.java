package com.accenture.archidroid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
public class Movie extends BaseModel implements Serializable {

    @SerializedName("imdbID")
    public String imdbId;

    @SerializedName("Title")
    public String title;

    @SerializedName("Year")
    public String year;

    @SerializedName("Plot")
    public String plot;

    @SerializedName("Poster")
    public String posterUrl;

    @Override
    public String toString() {
        return title + " - " + year;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Movie && ((Movie)obj).imdbId != null && this.imdbId != null) {
            return ((Movie)obj).imdbId.equals(this.imdbId);
        }
        return false;
    }

}

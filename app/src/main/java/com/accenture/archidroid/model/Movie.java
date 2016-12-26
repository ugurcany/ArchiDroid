package com.accenture.archidroid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
public class Movie implements Serializable {

    @SerializedName("imdbID")
    public String imdbId;

    @SerializedName("Title")
    public String title;

    @SerializedName("Year")
    public String year;

    @SerializedName("Poster")
    public String posterUrl;

    @Override
    public String toString() {
        return title + " - " + year;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Movie) {
            return ((Movie) obj).imdbId.equals(this.imdbId);
        }
        return false;
    }

}

package com.accenture.archidroid;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by ugurcan.yildirim on 4.01.2017.
 */
public class Scope {

    @javax.inject.Scope
    @Retention(RetentionPolicy.RUNTIME)
    public @interface AppScope {}

    @javax.inject.Scope
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ActivityScope {}

}
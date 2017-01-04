package com.accenture.archidroid.logic.rest;

import com.accenture.archidroid.logic.scope.AppScope;

import dagger.Component;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
@AppScope
@Component(modules={RestModule.class})
public interface RestComponent {

    RestApi restApi();

}

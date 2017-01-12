package com.accenture.archidroid.logic.rest;

import com.accenture.archidroid.Scope;

import dagger.Component;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
@Scope.AppScope
@Component(modules={RestModule.class})
public interface RestComponent {

    RestApi restApi();

}

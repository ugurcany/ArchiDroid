package com.accenture.archidroid.event;

import com.accenture.archidroid.model.BaseModel;

/**
 * Created by ugurcan.yildirim on 28.12.2016.
 */
public class BaseEvent<D extends BaseModel> {

    public String key;
    public D data;

    public boolean success;

}

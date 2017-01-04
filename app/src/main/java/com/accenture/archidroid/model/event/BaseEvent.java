package com.accenture.archidroid.model.event;

import com.accenture.archidroid.model.data.BaseData;

/**
 * Created by ugurcan.yildirim on 28.12.2016.
 */
public abstract class BaseEvent<D extends BaseData> {

    public String key;
    public D data;

    public boolean success;

}

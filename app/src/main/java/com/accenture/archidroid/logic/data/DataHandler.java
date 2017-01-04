package com.accenture.archidroid.logic.data;

import com.accenture.archidroid.event.BaseEvent;
import com.accenture.archidroid.model.BaseModel;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
public class DataHandler<D extends BaseModel, E extends BaseEvent<D>> {

    private String key = null;

    private D data = null;
    private E event = null;

    public DataHandler(Class<D> dataClass, Class<E> eventClass){
        try {
            data = dataClass.newInstance();
            event = eventClass.newInstance();
        }catch (Exception ignored){}
    }

    public boolean hasData(String key){
        return this.key != null && this.key.equals(key);
    }

    public boolean setData(String key, D newData){
        //IF SAME DATA IS ALREADY SET --> EXIT
        if(this.key != null && this.key.equals(key) && this.data != null && this.data.equals(newData)){
            return false;
        }
        else{ //NEW DATA ARRIVED
            this.key = key;
            this.data = newData;

            return true;
        }
    }

    public void postData(boolean success){
        event.key = key;
        event.success = success;

        if(success){
            event.data = data;
            EventBus.getDefault().post(event);
        }
        else{
            event.data = null;
            EventBus.getDefault().post(event);
        }
    }

}

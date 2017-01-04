package com.accenture.archidroid.logic;

import android.util.Log;

import com.accenture.archidroid.logic.rest.RestApi;
import com.accenture.archidroid.model.data.BaseData;

import java.lang.reflect.Method;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
public class Executor<D extends BaseData> {

    private final String TAG = getClass().getSimpleName();

    private DataHandler dataHandler;
    private RestApi restApi;
    private Method method;

    public Executor(RestApi restApi, DataHandler dataHandler, String serviceName){
        this.dataHandler = dataHandler;
        this.restApi = restApi;

        try { //FIND CORRESPONDING REST API METHOD
            for(Method method : restApi.getClass().getDeclaredMethods()){
                if(method.getName().equalsIgnoreCase(serviceName)){
                    this.method = method;
                    break;
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void execute(final String key, final String... params){
        //POST DATA IF ALREADY EXISTS
        if(dataHandler.hasData(key)){
            dataHandler.postData(true);
            Log.d(TAG, "Data with key \"" + key + "\" already exists! Posted!");
        }

        //SERVICE CALL
        try{
            Call<D> call = (Call<D>) method.invoke(restApi, params);

            call.enqueue(new Callback<D>() {
                @Override
                public void onResponse(Call<D> call, Response<D> response) {
                    if (response.isSuccessful()) {
                        D data = response.body();

                        boolean isNewDataSet = dataHandler.setData(key, data);
                        if (isNewDataSet) {
                            dataHandler.postData(true);
                            Log.d(TAG, "Data with key \"" + key + "\" posted!");
                        }
                    } else {
                        dataHandler.postData(false);
                    }
                }

                @Override
                public void onFailure(Call<D> call, Throwable t) {
                    dataHandler.postData(false);
                }
            });
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}

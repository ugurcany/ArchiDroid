package com.accenture.archidroid.logic.rest;

import com.accenture.archidroid.BuildConfig;
import com.accenture.archidroid.logic.scope.AppScope;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
@Module
public class RestModule {

    private final String BASE_URL = "http://www.omdbapi.com/";

    @Provides
    @AppScope
    OkHttpClient providesHttpClient() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS);

        if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(interceptor);
        }

        return clientBuilder.build();
    }

    @Provides
    @AppScope
    Retrofit providesRetrofit(OkHttpClient httpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @AppScope
    RestApi providesRestApi(Retrofit retrofit) {
        return retrofit.create(RestApi.class);
    }

}

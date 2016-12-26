package com.accenture.archidroid.dagger.main;

import com.accenture.archidroid.BuildConfig;
import com.accenture.archidroid.model.Movies;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ugurcan.yildirim on 26.12.2016.
 */
@Module
public class RestModule {

    private final String BASE_URL = "http://www.omdbapi.com/";

    @Provides
    @Singleton
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
    @Singleton
    Retrofit providesRetrofit(OkHttpClient httpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    Api providesApi(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }

    //REST API
    public interface Api {

        @GET("/")
        Call<Movies> getMovies(
                @Query("s") String searchKey,
                @Query("r") String responseType
        );

    }

}

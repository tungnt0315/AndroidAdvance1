package com.example.tung.lesson6_retrofit_okhttp.restapi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tung on 5/16/17.
 */

public class ServiceGenerator {
    private static final String BASE_URL = "https://restcountries.eu/";

    private static Retrofit retrofit;

    private static Retrofit.Builder reBuilder = new Retrofit.Builder()
                                                .baseUrl(BASE_URL)
                                                .addConverterFactory(GsonConverterFactory.create());

    private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor()
                                                                        .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient.Builder okBuilder = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor);

    private static OkHttpClient okHttpClient = okBuilder.build();

    public static <T> T createService(Class<T> serviceClass){
        if (retrofit == null) {
            retrofit = reBuilder.client(okHttpClient).build();
        }
        return retrofit.create(serviceClass);
    }


}

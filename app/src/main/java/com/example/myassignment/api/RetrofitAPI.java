package com.example.myassignment.api;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shabbir on 26/4/21$.
 */
public class RetrofitAPI {

   //public static String mBaseUrl = BuildConfig.BASE_URL;
   public static String mBaseUrl = "https://api.themoviedb.org/3/";
    static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);


    static Interceptor networkInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            //Request request = chain.request().newBuilder().addHeader("Content-Type", "application/json").build();
            Request request = chain.request().newBuilder().build();
            return chain.proceed(request);
        }
    };

    static OkHttpClient client = new OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES).writeTimeout(1, TimeUnit.MINUTES).addInterceptor(interceptor)
            .addNetworkInterceptor(networkInterceptor)
            .build();

    private static Gson mGson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            return false;
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }
    }).create();

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(mBaseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(mGson))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            //.addCallAdapterFactory(new LiveDataCallAdapterFactory()).build();

            .build();


    public static <S> S cteateService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

}

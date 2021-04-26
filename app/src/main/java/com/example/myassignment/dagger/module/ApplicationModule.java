package com.example.myassignment.dagger.module;

import android.content.Context;


import com.example.myassignment.App;
import com.example.myassignment.utility.Utils;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Shabbir on 25/4/21$.
 */
@Module
public class ApplicationModule {


    private App mApp;

    public ApplicationModule(App app) {
        mApp = app;
    }

    @Provides
    @Singleton
    public Context appContext() {
        return mApp;
    }

    @Provides
    @Singleton
    public Utils utils() {
        return new Utils();
    }


    @Provides
    @Singleton
    public Gson getGson() {
        GsonBuilder sGsonBuilder = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        });
        return sGsonBuilder.create();
    }


}

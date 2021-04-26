package com.example.myassignment;


import android.app.Application;

import com.example.myassignment.dagger.component.ActivityComponent;
import com.example.myassignment.dagger.component.AppComponent;
import com.example.myassignment.dagger.component.DaggerAppComponent;
import com.example.myassignment.dagger.module.ApplicationModule;


public class App extends Application {


    private AppComponent mAppComponent;
    private ActivityComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder().applicationModule(new ApplicationModule(this))
                .build();


    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }






}

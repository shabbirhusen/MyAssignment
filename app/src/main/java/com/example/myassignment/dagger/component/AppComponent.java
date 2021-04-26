package com.example.myassignment.dagger.component;


import com.example.myassignment.dagger.module.ApplicationModule;
import com.example.myassignment.utility.Utils;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by Shabbir on 25/4/21$.
 */
@Singleton
@Component(modules = {ApplicationModule.class})

public interface AppComponent {

    Utils utils();

    Gson getGson();


}

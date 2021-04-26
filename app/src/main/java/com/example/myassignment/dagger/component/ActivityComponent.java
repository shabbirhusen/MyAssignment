package com.example.myassignment.dagger.component;



import com.example.myassignment.ui.LoginActivity;
import com.example.myassignment.ui.MovieListActivity;
import com.example.myassignment.dagger.scope.ActivityScope;

import dagger.Component;

/**
 * Created by Shabbir on 25/4/21$.
 */
@ActivityScope
@Component(dependencies = AppComponent.class)
public interface ActivityComponent {

    void inject(LoginActivity loginActivity);
    void inject(MovieListActivity movieListActivity);



}

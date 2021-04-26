package com.example.myassignment

import androidx.lifecycle.ViewModel
import com.example.myassignment.ui.MovieListViewModel
import dagger.MapKey
import kotlin.reflect.KClass

// TODO: What am I doing here?

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<MovieListViewModel>)
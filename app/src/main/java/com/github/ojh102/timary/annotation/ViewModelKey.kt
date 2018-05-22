package com.github.ojh102.timary.annotation

import androidx.lifecycle.ViewModel
import com.github.ojh102.timary.base.BaseViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

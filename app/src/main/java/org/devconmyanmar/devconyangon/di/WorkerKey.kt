package org.devconmyanmar.devconyangon.di

import androidx.work.ListenableWorker
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Created by Vincent on 12/17/19
 */
@MapKey
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class WorkerKey(val value: KClass<out ListenableWorker>)

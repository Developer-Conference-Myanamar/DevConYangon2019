package org.devconmyanmar.devconyangon.di

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module

/**
 * Created by Vincent on 12/17/19
 */
@Module(includes = [AssistedInject_AssistedInjectModule::class])
@AssistedModule
interface AssistedInjectModule
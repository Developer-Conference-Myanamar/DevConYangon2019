package org.devconmyanmar.devconyangon.base.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import org.devconmyanmar.devconyangon.base.di.module.BaseAppModule.Provider
import org.devconmyanmar.devconyangon.base.di.viewmodel.ViewModelFactoryModule

/**
 * Created by Vincent on 12/6/18
 */
@Module(includes = [Provider::class, ViewModelFactoryModule::class, RepositoryModule::class])
abstract class BaseAppModule {

  @Module
  object Provider {

    @Provides fun context(application: Application): Context {
      return application.applicationContext
    }

  }

}


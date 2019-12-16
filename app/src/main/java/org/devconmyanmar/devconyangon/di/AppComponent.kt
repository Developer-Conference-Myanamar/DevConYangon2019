package org.devconmyanmar.devconyangon.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import org.devconmyanmar.devconyangon.DevConApp
import org.devconmyanmar.devconyangon.feature.sync.DaggerWorkerFactory
import javax.inject.Singleton

/**
 * Created by Vincent on 2019-06-20
 */
@Singleton
@Component(
  modules = [AppModule::class,
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    AssistedInjectModule::class]
)
interface AppComponent {

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: Application): Builder

    fun build(): AppComponent
  }

  fun inject(application: DevConApp)

  fun factory(): DaggerWorkerFactory

}
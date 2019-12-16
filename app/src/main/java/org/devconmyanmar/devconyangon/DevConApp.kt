package org.devconmyanmar.devconyangon

import android.app.Application
import android.os.Build
import androidx.work.Configuration
import androidx.work.WorkManager
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import org.devconmyanmar.devconyangon.base.di.AppInjector
import org.devconmyanmar.devconyangon.di.DaggerAppComponent
import org.devconmyanmar.devconyangon.feature.sync.DaggerWorkerFactory
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

/**
 * Created by Vincent on 2019-11-01
 */
class DevConApp : Application(), HasAndroidInjector {

  @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

  override fun androidInjector(): AndroidInjector<Any> {
    return dispatchingAndroidInjector
  }

  override fun onCreate() {
    super.onCreate()
    AndroidThreeTen.init(this)

    val appComponent = DaggerAppComponent.builder()
      .application(this)
      .build()

    appComponent.inject(this)

    AppInjector.initAutoInjection(this)

    val factory: DaggerWorkerFactory = appComponent.factory()
    WorkManager.initialize(this, Configuration.Builder().setWorkerFactory(factory).build())

    if (BuildConfig.DEBUG) {
      Timber.plant(DebugTree())
      if (!isRoboUnitTest()) {
        FlipperInitializer.initFlipper(this)
      }
    }

  }

  fun isRoboUnitTest(): Boolean {
    return "robolectric" == Build.FINGERPRINT
  }
}
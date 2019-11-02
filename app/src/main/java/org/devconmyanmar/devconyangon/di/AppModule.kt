package org.devconmyanmar.devconyangon.di

import android.content.Context
import coil.ImageLoader
import coil.ImageLoaderBuilder
import dagger.Module
import dagger.Provides
import org.devconmyanmar.devconyangon.base.di.module.BaseAppModule
import org.devconmyanmar.devconyangon.di.AppModule.Provider
import org.devconmyanmar.devconyangon.domain.helper.Zones
import org.devconmyanmar.devconyangon.feature.home.HomeFeatureModule
import org.threeten.bp.Clock
import javax.inject.Singleton

/**
 * Created by Vincent on 2019-06-20
 */
@Module(
  includes = [
    Provider::class,
    BaseAppModule::class,
    HomeFeatureModule::class
  ]
)
abstract class AppModule {

  @Module
  object Provider {

    @Provides @Singleton @JvmStatic
    fun imageLoader(context: Context): ImageLoader {
      return ImageLoaderBuilder(context)
//        .placeholder(R.drawable.placeholder_rect)
        .build()
    }

    @Provides @Singleton @JvmStatic
    fun clock(): Clock {
      return Clock.system(Zones.YANGON)
    }

  }
}
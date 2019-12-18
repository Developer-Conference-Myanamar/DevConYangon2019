package org.devconmyanmar.devconyangon.di

import android.content.Context
import coil.ImageLoader
import coil.ImageLoaderBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import org.devconmyanmar.devconyangon.base.di.module.BaseAppModule
import org.devconmyanmar.devconyangon.di.AppModule.Provider
import org.devconmyanmar.devconyangon.domain.exception.ExceptionToStringMapper
import org.devconmyanmar.devconyangon.domain.helper.Zones
import org.devconmyanmar.devconyangon.exception.ExceptionToStringMapperImpl
import org.devconmyanmar.devconyangon.feature.about.Infofragments.sponsor.SponsorFeatureModule
import org.devconmyanmar.devconyangon.feature.home.HomeFeatureModule
import org.devconmyanmar.devconyangon.feature.notification.NotificationModule
import org.devconmyanmar.devconyangon.feature.sessiondetail.SessionDetailFeatureModule
import org.devconmyanmar.devconyangon.feature.speakerdetail.SpeakerDetailFeatureModule
import org.devconmyanmar.devconyangon.feature.sync.SyncFeatureModule
import org.threeten.bp.Clock
import javax.inject.Singleton

/**
 * Created by Vincent on 2019-06-20
 */
@Module(
  includes = [
    Provider::class,
    BaseAppModule::class,
    HomeFeatureModule::class,
    SessionDetailFeatureModule::class,
    SpeakerDetailFeatureModule::class,
    SponsorFeatureModule::class,
    SyncFeatureModule::class,
    NotificationModule::class
  ]
)
abstract class AppModule {

  @Binds
  abstract fun exceptionToStringMapper(exceptionToStringMapper: ExceptionToStringMapperImpl): ExceptionToStringMapper

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
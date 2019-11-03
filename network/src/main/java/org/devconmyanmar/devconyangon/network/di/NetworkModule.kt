package org.devconmyanmar.devconyangon.network.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.devconmyanmar.devconyangon.data.datasource.SessionNetworkDataSource
import org.devconmyanmar.devconyangon.network.BuildConfig
import org.devconmyanmar.devconyangon.network.SessionNetworkDataSourceRealImpl
import org.devconmyanmar.devconyangon.network.di.NetworkModule.Provider
import javax.inject.Singleton

/**
 * Created by Vincent on 2019-09-10
 */
@Module(includes = [RetrofitModule::class, Provider::class])
abstract class NetworkModule {

  @Binds
  abstract fun sessionNetworkDataSource(sessionNetworkDataSource: SessionNetworkDataSourceRealImpl): SessionNetworkDataSource

  @Module
  object Provider {

    @Provides @Singleton
    fun okHttpClient(): OkHttpClient {

      val okHttpClientBuilder = OkHttpClient.Builder()

      if (BuildConfig.DEBUG) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
      }

      val okHttpClient = okHttpClientBuilder.build()

      return okHttpClient
    }

  }

}

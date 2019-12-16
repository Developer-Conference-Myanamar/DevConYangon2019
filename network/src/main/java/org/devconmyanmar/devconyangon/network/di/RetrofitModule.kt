package org.devconmyanmar.devconyangon.network.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import org.devconmyanmar.devconyangon.network.BuildConfig
import org.devconmyanmar.devconyangon.network.DevconYangonApi
import org.devconmyanmar.devconyangon.network.di.RetrofitModule.Provider
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by Vincent on 2019-09-10
 */
@Module(includes = [Provider::class])
abstract class RetrofitModule {

  @Module
  object Provider {

    @Provides @Singleton fun retrofit(
      okHttpClient: OkHttpClient
    ): Retrofit {

      val baseUrl = "${BuildConfig.BASE_URL}"

      val moshi = Moshi.Builder()
        .build()

      return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()
    }

    @Provides fun devconYangonApi(retrofit: Retrofit): DevconYangonApi {
      return retrofit.create(DevconYangonApi::class.java)
    }
  }

}
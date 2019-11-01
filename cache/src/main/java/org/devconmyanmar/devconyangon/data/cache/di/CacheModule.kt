package org.devconmyanmar.devconyangon.data.cache.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Vincent on 2019-06-20
 */
@Module(includes = [CacheModule.Providers::class])
abstract class CacheModule {


  @Module
  internal object Providers {

//    @Provides
//    @Singleton
//    fun sqlDriver(context: Context): SqlDriver {
//      return AndroidSqliteDriver(DevConYangonDb.Schema, context, "devcon_ygn.db")
//    }
//
//    @Provides
//    @Singleton
//    fun database(sqlDriver: SqlDriver): DevConYangonDb {
//
//     return DevConYangonDb(sqlDriver)
//    }

    @Provides
    fun sharedPref(context: Context): SharedPreferences {
      return PreferenceManager.getDefaultSharedPreferences(context)
    }

  }

}
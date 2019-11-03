package org.devconmyanmar.devconyangon.base.di.module

import dagger.Binds
import dagger.Module
import org.devconmyanmar.devconyangon.data.SessionRepositoryRealImpl
import org.devconmyanmar.devconyangon.data.cache.di.CacheModule
import org.devconmyanmar.devconyangon.domain.repository.SessionRepository
import org.devconmyanmar.devconyangon.network.di.NetworkModule

/**
 * Created by Vincent on 12/6/18
 */
@Module(includes = [CacheModule::class, NetworkModule::class])
abstract class RepositoryModule {

  @Binds
  abstract fun sessionRepository(sessionRepositoryReal: SessionRepositoryRealImpl): SessionRepository

}
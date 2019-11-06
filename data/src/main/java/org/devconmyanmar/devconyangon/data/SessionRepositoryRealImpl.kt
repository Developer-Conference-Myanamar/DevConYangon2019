package org.devconmyanmar.devconyangon.data

import org.devconmyanmar.devconyangon.data.datasource.SessionCacheDataSource
import org.devconmyanmar.devconyangon.data.datasource.SessionNetworkDataSource
import org.devconmyanmar.devconyangon.data.entity.SessionEntityToListingMapper
import org.devconmyanmar.devconyangon.data.exception.EmptyDataException
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.model.SessionListing
import org.devconmyanmar.devconyangon.domain.repository.SessionRepository
import org.threeten.bp.LocalDate
import javax.inject.Inject

/**
 * Created by Vincent on 11/3/19
 */
class SessionRepositoryRealImpl @Inject constructor(
  private val sessionNetworkDataSource: SessionNetworkDataSource,
  private val sessionCacheDataSource: SessionCacheDataSource,
  private val sessionEntityToListingMapper: SessionEntityToListingMapper
) : SessionRepository {
  override suspend fun getSessionListing(date: LocalDate): List<SessionListing> {

    var networkException: Exception? = null
    try {
      val networkData = sessionNetworkDataSource.getSessions(date)
      sessionCacheDataSource.putSessionEntities(networkData)
    } catch (exception: Exception) {
      networkException = exception
    }

    val dataFromCache = sessionCacheDataSource.getSessionEntities(date)

    if (dataFromCache.isEmpty()) {
      if (networkException != null) {
        throw networkException
      } else {
//        throw EmptyDataException()
      }
    }

    return dataFromCache.map(sessionEntityToListingMapper::map)
  }

  override suspend fun getFavoriteSessions(date: LocalDate): List<SessionListing> {
    val dataFromCache = sessionCacheDataSource.getFavoriteSessionEntities(date)

//    if (dataFromCache.isEmpty()) {
//      throw EmptyDataException()
//    }
    return dataFromCache.map(sessionEntityToListingMapper::map)
  }

  override suspend fun toggleFavoriteStatus(sessionId: SessionId) {
    val currentStatus = sessionCacheDataSource.getFavoriteStatus(sessionId)
    sessionCacheDataSource.updateFavoriteStatus(sessionId, currentStatus.not())
  }

}
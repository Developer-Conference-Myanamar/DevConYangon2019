package org.devconmyanmar.devconyangon.data

import org.devconmyanmar.devconyangon.data.datasource.NetworkDataSource
import org.devconmyanmar.devconyangon.data.datasource.SessionCacheDataSource
import org.devconmyanmar.devconyangon.data.entity.SessionEntityMapper
import org.devconmyanmar.devconyangon.domain.model.Session
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.model.SpeakerId
import org.devconmyanmar.devconyangon.domain.repository.SessionRepository
import org.threeten.bp.LocalDate
import javax.inject.Inject

/**
 * Created by Vincent on 11/3/19
 */
class SessionRepositoryRealImpl @Inject constructor(
  private val networkDataSource: NetworkDataSource,
  private val sessionCacheDataSource: SessionCacheDataSource,
  private val sessionEntityMapper: SessionEntityMapper
) : SessionRepository {

  override suspend fun getSession(sessionId: SessionId): Session {

    val data = sessionCacheDataSource.getSessionEntity(sessionId)

    return sessionEntityMapper.map(data)
  }

  override suspend fun getSessionListing(date: LocalDate): List<Session> {
    val dataFromCache = sessionCacheDataSource.getSessionEntities(date)
    return dataFromCache.map(sessionEntityMapper::map)
  }

  override suspend fun getFavoriteSessions(date: LocalDate): List<Session> {
    val dataFromCache = sessionCacheDataSource.getFavoriteSessionEntities(date)

//    if (dataFromCache.isEmpty()) {
//      throw EmptyDataException()
//    }
    return dataFromCache.map(sessionEntityMapper::map)
  }

  override suspend fun toggleFavoriteStatus(sessionId: SessionId) {
    val currentStatus = sessionCacheDataSource.getFavoriteStatus(sessionId)
    sessionCacheDataSource.updateFavoriteStatus(sessionId, currentStatus.not())
  }

  override suspend fun getSessionOfSpeaker(speakerId: SpeakerId): List<Session> {
    val sessionOfSpeaker = sessionCacheDataSource.getSessionEntitiesOfSpeaker(speakerId)
    return sessionOfSpeaker.map(sessionEntityMapper::map)
  }

  override suspend fun downloadSessions() {
    val sessionList = networkDataSource.getAllSession()
    sessionCacheDataSource.putSessionEntities(sessionList)
  }

  override suspend fun getAllSessions(): List<Session> {
    return sessionCacheDataSource.getAllSessions().map(sessionEntityMapper::map)
  }

}
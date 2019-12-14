package org.devconmyanmar.devconyangon.data.cache

import org.devconmyanmar.devconyangon.DevConYangonDb
import org.devconmyanmar.devconyangon.data.cache.mapper.SessionTableMapper
import org.devconmyanmar.devconyangon.data.datasource.SessionCacheDataSource
import org.devconmyanmar.devconyangon.data.entity.SessionEntity
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.model.SpeakerId
import org.threeten.bp.LocalDate
import javax.inject.Inject

/**
 * Created by Vincent on 11/3/19
 */
class SessionCacheDataSourceImpl @Inject constructor(
  private val db: DevConYangonDb,
  private val sessionTableMapper: SessionTableMapper
) : SessionCacheDataSource {

  override fun putSessionEntities(sessionEntities: List<SessionEntity>) {
    db.transaction {
      sessionEntities.forEach { sessionEntity ->
        db.roomTableQueries.insert_or_replace(
          sessionEntity.room.roomId,
          sessionEntity.room.roomName
        )

        db.sessionTableQueries.insert_or_replace(
          sessionEntity.sessionId,
          sessionEntity.sessionTitle,
          sessionEntity.abstract,
          sessionEntity.date,
          sessionEntity.startTime,
          sessionEntity.endTime,
          sessionEntity.room.roomId
        )
        sessionEntity.speakers.forEach { speakerIds ->
          db.sessionSpeakerTableQueries.insert_or_replace(
            sessionEntity.sessionId,
            speakerIds
          )
        }
      }
    }
  }

  override fun getSessionEntities(date: LocalDate): List<SessionEntity> {
    return db.sessionTableQueries.select_all_at_date(date).executeAsList()
      .map(sessionTableMapper::map)
  }

  override fun getSessionEntity(sessionId: SessionId): SessionEntity {
    val queryResult = db.sessionTableQueries.select_by_id(sessionId).executeAsOne()

    return sessionTableMapper.map(queryResult)
  }

  override fun getFavoriteSessionEntities(date: LocalDate): List<SessionEntity> {
    return db.sessionTableQueries.select_favorite_at_date(date).executeAsList()
      .map(sessionTableMapper::map)
  }

  override fun getFavoriteStatus(sessionId: SessionId): Boolean {
    val queryResult =
      db.favoriteSessionTableQueries.select_with_session_id(sessionId).executeAsOneOrNull()
    return queryResult != null
  }

  override fun updateFavoriteStatus(sessionId: SessionId, favoriteStatus: Boolean) {

    if (favoriteStatus) {
      db.favoriteSessionTableQueries.insert_or_replace(sessionId)
    } else {
      db.favoriteSessionTableQueries.delete_with_session_id(sessionId)
    }
  }

  override fun getSessionEntitiesOfSpeaker(speakerId: SpeakerId): List<SessionEntity> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}


package org.devconmyanmar.devconyangon.data.cache

import org.devconmyanmar.devconyangon.DevConYangonDb
import org.devconmyanmar.devconyangon.data.cache.mapper.SessionTableMapper
import org.devconmyanmar.devconyangon.data.datasource.SessionCacheDataSource
import org.devconmyanmar.devconyangon.data.entity.SessionEntity
import org.devconmyanmar.devconyangon.domain.helper.Zones
import org.devconmyanmar.devconyangon.domain.model.SessionId
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

        val dateTime = sessionEntity.dateTimeInInstant.atZone(Zones.YANGON)

        db.sessionTableQueries.insert_or_replace(
          sessionEntity.sessionId,
          sessionEntity.sessionTitle,
          dateTime.toLocalDate(),
          dateTime.toLocalTime(),
          sessionEntity.room.roomId
        )
        sessionEntity.speakers.forEach { speakerEntity ->
          db.speakerTableQueries.insert_or_replace(speakerEntity.speakerId, speakerEntity.name)
          db.sessionSpeakerTableQueries.insert_or_replace(
            sessionEntity.sessionId,
            speakerEntity.speakerId
          )
        }
      }
    }
  }

  override fun getSessionEntities(date: LocalDate): List<SessionEntity> {
    return db.sessionTableQueries.select_all_at_date(date).executeAsList()
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
}


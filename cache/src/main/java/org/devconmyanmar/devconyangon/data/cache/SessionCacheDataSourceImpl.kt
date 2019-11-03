package org.devconmyanmar.devconyangon.data.cache

import org.devconmyanmar.devconyangon.DevConYangonDb
import org.devconmyanmar.devconyangon.data.datasource.SessionCacheDataSource
import org.devconmyanmar.devconyangon.data.entity.RoomEntity
import org.devconmyanmar.devconyangon.data.entity.SessionEntity
import org.devconmyanmar.devconyangon.data.entity.SpeakerEntity
import org.devconmyanmar.devconyangon.domain.helper.Zones
import org.threeten.bp.LocalDate
import org.threeten.bp.ZonedDateTime
import javax.inject.Inject

/**
 * Created by Vincent on 11/3/19
 */
class SessionCacheDataSourceImpl @Inject constructor(
  private val db: DevConYangonDb
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
    return db.sessionTableQueries.select_all_at_date(
      date = date,
      mapper = { sessionId, sessionTitle, localDate, localTime, room ->
        val roomQuery = db.roomTableQueries.select_by_id(room).executeAsOne()
        val speakerQuery = db.speakerTableQueries.select_by_session(sessionId).executeAsList()

        val dateTime = ZonedDateTime.of(localDate, localTime, Zones.YANGON)

        SessionEntity(
          sessionId = sessionId,
          sessionTitle = sessionTitle,
          dateTimeInInstant = dateTime.toInstant(),
          room = RoomEntity(
            roomQuery.room_id,
            roomQuery.room_name
          ),
          speakers = speakerQuery.map {
            SpeakerEntity(
              speakerId = it.speaker_id,
              name = it.speaker_title
            )
          }
        )
      }
    ).executeAsList()
  }
}


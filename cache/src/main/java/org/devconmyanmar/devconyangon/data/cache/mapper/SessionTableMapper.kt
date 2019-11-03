package org.devconmyanmar.devconyangon.data.cache.mapper

import org.devconmyanmar.devconyangon.DevConYangonDb
import org.devconmyanmar.devconyangon.data.cache.SessionTable
import org.devconmyanmar.devconyangon.data.entity.RoomEntity
import org.devconmyanmar.devconyangon.data.entity.SessionEntity
import org.devconmyanmar.devconyangon.data.entity.SpeakerEntity
import org.devconmyanmar.devconyangon.domain.mapper.UnidirectionalMap
import org.threeten.bp.Clock
import org.threeten.bp.ZonedDateTime
import javax.inject.Inject

/**
 * Created by Vincent on 11/3/19
 */
class SessionTableMapper @Inject constructor(
  private val db: DevConYangonDb,
  private val clock: Clock
) : UnidirectionalMap<SessionTable, SessionEntity> {

  override fun map(item: SessionTable): SessionEntity {

    val roomQuery = db.roomTableQueries.select_by_id(item.room).executeAsOne()
    val speakerQuery = db.speakerTableQueries.select_by_session(item.session_id).executeAsList()

    val dateTime = ZonedDateTime.of(item.date, item.time, clock.zone)

    return SessionEntity(
      sessionId = item.session_id,
      sessionTitle = item.session_title,
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
      },
      isFavorite = item.is_favourite
    )
  }
}


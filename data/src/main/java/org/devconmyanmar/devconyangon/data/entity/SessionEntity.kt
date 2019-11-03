package org.devconmyanmar.devconyangon.data.entity

import org.devconmyanmar.devconyangon.domain.mapper.UnidirectionalMap
import org.devconmyanmar.devconyangon.domain.model.Session
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.model.SessionListing
import org.threeten.bp.Clock
import org.threeten.bp.Instant
import javax.inject.Inject

/**
 * Created by Vincent on 11/3/19
 */
data class SessionEntity(
  val sessionId: SessionId,
  val sessionTitle: String,
  val dateTimeInInstant: Instant,
  val room: RoomEntity,
  val speakers: List<SpeakerEntity>
)

class SessionEntityMapper @Inject constructor(
  private val roomEntityMapper: RoomEntityMapper,
  private val speakerEntityMapper: SpeakerEntityMapper,
  private val clock: Clock
) : UnidirectionalMap<SessionEntity, Session> {

  override fun map(item: SessionEntity): Session {
    return Session(
      sessionId = item.sessionId,
      sessionTitle = item.sessionTitle,
      dateTime = item.dateTimeInInstant.atZone(clock.zone),
      room = roomEntityMapper.map(item.room),
      speakers = item.speakers.map(speakerEntityMapper::map)
    )
  }

}

class SessionEntityToListingMapper @Inject constructor(
  private val roomEntityMapper: RoomEntityMapper,
  private val speakerEntityMapper: SpeakerEntityMapper,
  private val clock: Clock
) : UnidirectionalMap<SessionEntity, SessionListing> {

  override fun map(item: SessionEntity): SessionListing {
    return SessionListing(
      sessionId = item.sessionId,
      sessionTitle = item.sessionTitle,
      dateTime = item.dateTimeInInstant.atZone(clock.zone),
      room = roomEntityMapper.map(item.room),
      speakers = item.speakers.map(speakerEntityMapper::map)
    )
  }

}

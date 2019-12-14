package org.devconmyanmar.devconyangon.data.entity

import org.devconmyanmar.devconyangon.data.datasource.SpeakerCacheDataSource
import org.devconmyanmar.devconyangon.domain.mapper.UnidirectionalMap
import org.devconmyanmar.devconyangon.domain.model.Session
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.model.SpeakerId
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import javax.inject.Inject

/**
 * Created by Vincent on 11/3/19
 */
data class SessionEntity(
  val sessionId: SessionId,
  val sessionTitle: String,
  val abstract: String,
  val date: LocalDate,
  val startTime: LocalTime,
  val endTime: LocalTime,
  val room: RoomEntity,
  val speakers: List<SpeakerId>,
  val isFavorite: Boolean
)

class SessionEntityMapper @Inject constructor(
  private val roomEntityMapper: RoomEntityMapper,
  private val speakerEntityMapper: SpeakerEntityMapper,
  private val speakerCacheDataSource: SpeakerCacheDataSource
) : UnidirectionalMap<SessionEntity, Session> {

  override fun map(item: SessionEntity): Session {

    return Session(
      sessionId = item.sessionId,
      sessionTitle = item.sessionTitle,
      abstract = item.abstract,
      date = item.date,
      startTime = item.startTime,
      endTime = item.endTime,
      room = roomEntityMapper.map(item.room),
      isFavorite = item.isFavorite,
      speakers =  speakerCacheDataSource.getSpeakerOfSession(item.sessionId).map(speakerEntityMapper::map)
    )
  }

}

package org.devconmyanmar.devconyangon.network

import org.devconmyanmar.devconyangon.data.datasource.SessionNetworkDataSource
import org.devconmyanmar.devconyangon.data.entity.RoomEntity
import org.devconmyanmar.devconyangon.data.entity.SessionEntity
import org.devconmyanmar.devconyangon.data.entity.SpeakerEntity
import org.devconmyanmar.devconyangon.domain.helper.Zones
import org.devconmyanmar.devconyangon.domain.model.RoomId
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.model.SpeakerId
import org.threeten.bp.LocalDate
import org.threeten.bp.ZonedDateTime
import javax.inject.Inject

/**
 * Created by Vincent on 11/3/19
 */
class SessionNetworkDataSourceRealImpl @Inject constructor() : SessionNetworkDataSource {

  override fun getSessions(date: LocalDate): List<SessionEntity> {
    return listOf(
      SessionEntity(
        sessionId = SessionId(0),
        sessionTitle = "Building a robust web System",
        dateTimeInInstant = ZonedDateTime.of(2019, 12, 21, 9, 0, 0, 0, Zones.YANGON).toInstant(),
        room = RoomEntity(
          roomId = RoomId(0),
          roomName = "Main Hall"
        ),
        speakers = listOf(
          SpeakerEntity(
            speakerId = SpeakerId(0),
            name = "Fake Wharton"
          )
        )
      ),
      SessionEntity(
        sessionId = SessionId(1),
        sessionTitle = "Android X and Y",
        dateTimeInInstant = ZonedDateTime.of(2019, 12, 21, 9, 0, 0, 0, Zones.YANGON).toInstant(),
        room = RoomEntity(
          roomId = RoomId(0),
          roomName = "203"
        ),
        speakers = listOf(
          SpeakerEntity(
            speakerId = SpeakerId(0),
            name = "John Doe"
          )
        )
      ),
      SessionEntity(
        sessionId = SessionId(2),
        sessionTitle = "Creating a good quality product",
        dateTimeInInstant = ZonedDateTime.of(2019, 12, 21, 10, 30, 0, 0, Zones.YANGON).toInstant(),
        room = RoomEntity(
          roomId = RoomId(0),
          roomName = "Main Hall"
        ),
        speakers = listOf(
          SpeakerEntity(
            speakerId = SpeakerId(0),
            name = "Walter White"
          )
        )
      )
    )
  }
}

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
        dateTimeInInstant = ZonedDateTime.of(2019, 11, 6, 9, 0, 0, 0, Zones.YANGON).toInstant(),
        room = RoomEntity(
          roomId = RoomId(0),
          roomName = "Main Hall"
        ),
        speakers = listOf(
          SpeakerEntity(
            speakerId = SpeakerId(0),
            name = "Fake Wharton"
          )
        ),
        isFavorite = false
      ),
      SessionEntity(
        sessionId = SessionId(1),
        sessionTitle = "Android X and Y",
        dateTimeInInstant = ZonedDateTime.of(2019, 11, 6, 9, 0, 0, 0, Zones.YANGON).toInstant(),
        room = RoomEntity(
          roomId = RoomId(1),
          roomName = "203"
        ),
        speakers = listOf(
          SpeakerEntity(
            speakerId = SpeakerId(1),
            name = "John Doe"
          )
        ),
        isFavorite = false
      ),
      SessionEntity(
        sessionId = SessionId(2),
        sessionTitle = "Creating a good quality product",
        dateTimeInInstant = ZonedDateTime.of(2019, 11, 6, 13, 30, 0, 0, Zones.YANGON).toInstant(),
        room = RoomEntity(
          roomId = RoomId(0),
          roomName = "Main Hall"
        ),
        speakers = listOf(
          SpeakerEntity(
            speakerId = SpeakerId(2),
            name = "Walter White"
          )
        ),
        isFavorite = false
      ),
      SessionEntity(
        sessionId = SessionId(3),
        sessionTitle = "Very Good Talk",
        dateTimeInInstant = ZonedDateTime.of(
          2019, 11, 6, 10, 30, 0, 0, Zones.YANGON
        ).toInstant(),
        room = RoomEntity(
          roomId = RoomId(2),
          roomName = "305"
        ),
        speakers = listOf(
          SpeakerEntity(
            speakerId = SpeakerId(3),
            name = "Very Good Person"
          )
        ),
        isFavorite = false
      ), SessionEntity(
        sessionId = SessionId(4),
        sessionTitle = "Very Good Talk 2",
        dateTimeInInstant = ZonedDateTime.of(
          2019, 11, 6, 10, 30, 0, 0, Zones.YANGON
        ).toInstant(),
        room = RoomEntity(
          roomId = RoomId(2),
          roomName = "305"
        ),
        speakers = listOf(
          SpeakerEntity(
            speakerId = SpeakerId(3),
            name = "Very Good Person"
          )
        ),
        isFavorite = false
      ), SessionEntity(
        sessionId = SessionId(5),
        sessionTitle = "Very Good Talk 3",
        dateTimeInInstant = ZonedDateTime.of(
          2019, 11, 6, 12, 30, 0, 0, Zones.YANGON
        ).toInstant(),
        room = RoomEntity(
          roomId = RoomId(2),
          roomName = "305"
        ),
        speakers = listOf(
          SpeakerEntity(
            speakerId = SpeakerId(3),
            name = "Very Good Person"
          )
        ),
        isFavorite = false
      ),
      SessionEntity(
        sessionId = SessionId(6),
        sessionTitle = "Very Good Talk 3",
        dateTimeInInstant = ZonedDateTime.of(
          2019, 11, 6, 13, 30, 0, 0, Zones.YANGON
        ).toInstant(),
        room = RoomEntity(
          roomId = RoomId(2),
          roomName = "305"
        ),
        speakers = listOf(
          SpeakerEntity(
            speakerId = SpeakerId(3),
            name = "Very Good Person"
          )
        ),
        isFavorite = false
      )
    )
  }
}

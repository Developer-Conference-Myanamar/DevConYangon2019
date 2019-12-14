package org.devconmyanmar.devconyangon.network

import org.devconmyanmar.devconyangon.data.datasource.NetworkDataSource
import org.devconmyanmar.devconyangon.data.entity.RoomEntity
import org.devconmyanmar.devconyangon.data.entity.SessionEntity
import org.devconmyanmar.devconyangon.data.entity.SpeakerEntity
import org.devconmyanmar.devconyangon.domain.model.RoomId
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.model.SpeakerId
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import org.threeten.bp.Month
import javax.inject.Inject

/**
 * Created by Vincent on 12/14/19
 */

class NetworkDataSourceImpl @Inject constructor() : NetworkDataSource {

  override fun getAllSpeakers(): List<SpeakerEntity> {
    return listOf(
      SpeakerEntity(
        speakerId = SpeakerId(0),
        name = "John Doe",
        position = "Networking Enginner",
        biography = "Biography",
        imageUrl = "lol",
        sessionList = listOf()
      ),
      SpeakerEntity(
        speakerId = SpeakerId(1),
        name = "Walter White",
        position = "Five Star Chef",
        biography = "Biography",
        imageUrl = "lol",
        sessionList = listOf()
      ),
      SpeakerEntity(
        speakerId = SpeakerId(2),
        name = "Very Good Person",
        position = "Very Good PM",
        biography = "Biography",
        imageUrl = "lol",
        sessionList = listOf()
      )
    )
  }

  override fun getAllSession(): List<SessionEntity> {
    return listOf(
      SessionEntity(
        sessionId = SessionId(0),
        sessionTitle = "Building a robust web System",
        abstract = "Abstract",
        date = LocalDate.of(
          2019,
          Month.DECEMBER.value,
          21
        ),
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 0),
        room = RoomEntity(
          roomId = RoomId(0),
          roomName = "Main Hall"
        ),
        speakers = listOf(SpeakerId(0)),
        isFavorite = false
      ),
      SessionEntity(
        sessionId = SessionId(1),
        sessionTitle = "Android X and Y",
        abstract = "Abstract",
        date = LocalDate.of(
          2019,
          Month.DECEMBER.value,
          21
        ),
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 0),
        room = RoomEntity(
          roomId = RoomId(1),
          roomName = "203"
        ),
        speakers = listOf(SpeakerId(1)),
        isFavorite = false
      ),
      SessionEntity(
        sessionId = SessionId(2),
        sessionTitle = "Creating a good quality product",
        abstract = "Abstract",
        date = LocalDate.of(
          2019,
          Month.DECEMBER.value,
          21
        ),
        startTime = LocalTime.of(13, 30),
        endTime = LocalTime.of(14, 30),
        room = RoomEntity(
          roomId = RoomId(0),
          roomName = "Main Hall"
        ),
        speakers = listOf(SpeakerId(2)),
        isFavorite = false
      ),
      SessionEntity(
        sessionId = SessionId(3),
        sessionTitle = "Very Good Talk",
        abstract = "Abstract",
        date = LocalDate.of(
          2019,
          Month.DECEMBER.value,
          22
        ),
        startTime = LocalTime.of(10, 30),
        endTime = LocalTime.of(11, 30),
        room = RoomEntity(
          roomId = RoomId(2),
          roomName = "305"
        ),
        speakers = listOf(SpeakerId(1), SpeakerId(2)),
        isFavorite = false
      ), SessionEntity(
        sessionId = SessionId(4),
        sessionTitle = "Very Good Talk 2",
        abstract = "Abstract",
        date = LocalDate.of(
          2019,
          Month.DECEMBER.value,
          22
        ),
        startTime = LocalTime.of(10, 30),
        endTime = LocalTime.of(11, 30),
        room = RoomEntity(
          roomId = RoomId(2),
          roomName = "305"
        ),
        speakers = listOf(SpeakerId(0)),
        isFavorite = false
      ), SessionEntity(
        sessionId = SessionId(5),
        sessionTitle = "Very Good Talk 3",
        abstract = "Abstract",
        date = LocalDate.of(
          2019,
          Month.DECEMBER.value,
          22
        ),
        startTime = LocalTime.of(12, 30),
        endTime = LocalTime.of(13, 30),
        room = RoomEntity(
          roomId = RoomId(2),
          roomName = "305"
        ),
        speakers = listOf(SpeakerId(2)),
        isFavorite = false
      ),
      SessionEntity(
        sessionId = SessionId(6),
        sessionTitle = "Very Good Talk 3",
        abstract = "Abstract",
        date = LocalDate.of(
          2019,
          Month.DECEMBER.value,
          21
        ),
        startTime = LocalTime.of(13, 30),
        endTime = LocalTime.of(15, 0),
        room = RoomEntity(
          roomId = RoomId(2),
          roomName = "305"
        ),
        speakers = listOf(SpeakerId(0)),
        isFavorite = false
      )
    )
  }

}
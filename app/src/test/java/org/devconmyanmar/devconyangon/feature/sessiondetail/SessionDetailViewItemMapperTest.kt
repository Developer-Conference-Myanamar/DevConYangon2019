package org.devconmyanmar.devconyangon.feature.sessiondetail

import org.devconmyanmar.devconyangon.domain.model.Room
import org.devconmyanmar.devconyangon.domain.model.RoomId
import org.devconmyanmar.devconyangon.domain.model.Session
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.model.Speaker
import org.devconmyanmar.devconyangon.domain.model.SpeakerId
import org.junit.Assert
import org.junit.Test
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import org.threeten.bp.Month

/**
 * Created by Vincent on 11/13/19
 */
class SessionDetailViewItemMapperTest {

  val sessionDetailViewItemMapper = SessionDetailViewItemMapper()

  @Test
  fun testMapping() {
    val input = Session(
      sessionId = SessionId(1),
      sessionTitle = "Title",
      abstract = "Abstract",
      date = LocalDate.of(2019, Month.DECEMBER, 21),
      startTime = LocalTime.of(9, 0),
      endTime = LocalTime.of(10, 0),
      room = Room(
        roomId = RoomId(0L),
        roomName = "Room Name"
      ),
      speakers = listOf(
        Speaker(
          speakerId = SpeakerId(0L),
          name = "Speaker Name",
          biography = "Biography",
          imageUrl = "speaker image",
          position = "Speaker Position"
        )
      ),
      isFavorite = true
    )

//    /Sat, 21-Dec, 9:00 AM - 10:00 AM

    val expected = SessionDetailViewItem(
      sessionId = SessionId(1),
      title = "Title",
      abstract = "Abstract",
      dateTime = "Sat, 21-Dec, 9:00 AM - 10:00 AM",
      roomId = RoomId(0L),
      roomName = "Room Name",
      speakers = listOf(
        SessionDetailSpeakerViewItem(
          speakerId = SpeakerId(0L),
          name = "Speaker Name",
          imageUrl = "speaker image",
          position = "Speaker Position"
        )
      ),
      isFavorite = true
    )

    val actual = sessionDetailViewItemMapper.map(input)

    Assert.assertEquals(expected, actual)

  }
}
package org.devconmyanmar.devconyangon.feature.sessiondetail

import org.devconmyanmar.devconyangon.domain.mapper.UnidirectionalMap
import org.devconmyanmar.devconyangon.domain.model.RoomId
import org.devconmyanmar.devconyangon.domain.model.Session
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.model.SpeakerId
import org.threeten.bp.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

/**
 * Created by Vincent on 11/12/19
 */
data class SessionDetailViewItem(
  val sessionId: SessionId,
  val title: String,
  val dateTime: String,
  val roomId: RoomId,
  val roomName: String,
  val abstract: String,
  val speakers: List<SessionDetailSpeakerViewItem>,
  val isFavorite: Boolean
)

data class SessionDetailSpeakerViewItem(
  val speakerId: SpeakerId,
  val name: String,
  val position: String,
  val imageUrl: String
)

class SessionDetailViewItemMapper @Inject constructor() :
  UnidirectionalMap<Session, SessionDetailViewItem> {

  //Sat, 21-Dec, 9:00 AM - 10:00 AM
  private val dateFormatter = DateTimeFormatter.ofPattern("EE, dd-MMM,", Locale.ENGLISH)
  private val timeFormatter = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH)

  override fun map(item: Session): SessionDetailViewItem {
    val dateTimeString =
      "${item.date.format(dateFormatter)} ${item.startTime.format(timeFormatter)} - ${item.endTime.format(
        timeFormatter
      )}"


    return SessionDetailViewItem(
      sessionId = item.sessionId,
      title = item.sessionTitle,
      abstract = item.abstract,
      dateTime = dateTimeString,
      roomId = item.room.roomId,
      roomName = item.room.roomName,
      speakers = item.speakers.map {
        SessionDetailSpeakerViewItem(
          it.speakerId,
          it.name,
          it.position,
          it.imageUrl
        )
      },
      isFavorite = item.isFavorite
    )
  }

}
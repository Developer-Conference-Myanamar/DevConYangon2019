package org.devconmyanmar.devconyangon.feature.schedule.session

import org.devconmyanmar.devconyangon.domain.helper.asDelimitedString
import org.devconmyanmar.devconyangon.domain.mapper.UnidirectionalMap
import org.devconmyanmar.devconyangon.domain.model.Session
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

/**
 * Created by Vincent on 2019-11-03
 */
data class SessionViewItem(
  val sessionId: SessionId,
  val sessionTitle: String,
  val timeInString: String,
  val amPmOfTime: String,
  val roomName: String,
  val speakerNames: String,
  val shouldShowTime: Boolean,
  val isFavorite: Boolean
)

class SessionViewItemListMapper @Inject constructor() :
  UnidirectionalMap<List<Session>, List<SessionViewItem>> {

  /**
   * This set controls whether shouldShowTime has already set to true
   * Generally we want first item among all others item with same time to be set to true
   */
  private val timeSet = mutableSetOf<LocalTime>()
  private val timeFormatter = DateTimeFormatter.ofPattern("hh:mm", Locale.ENGLISH)
  private val amPmFormatter = DateTimeFormatter.ofPattern("a", Locale.ENGLISH)

  override fun map(item: List<Session>): List<SessionViewItem> {
    timeSet.clear()

    val sortedList = item.sortedBy {
      it.startTime
    }

    return sortedList.map {
      val sessionTime = it.startTime
      val shouldShowTime = timeSet.add(sessionTime)

      SessionViewItem(
        sessionId = it.sessionId,
        sessionTitle = it.sessionTitle,
        timeInString = sessionTime.format(timeFormatter),
        amPmOfTime = sessionTime.format(amPmFormatter),
        shouldShowTime = shouldShowTime,
        roomName = it.room.roomName,
        speakerNames = it.speakers.map { it.name }.asDelimitedString(','),
        isFavorite = it.isFavorite
      )
    }
  }
}

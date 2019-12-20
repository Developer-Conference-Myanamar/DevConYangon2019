package org.devconmyanmar.devconyangon.feature.agenda.session

import org.devconmyanmar.devconyangon.domain.helper.asDelimitedString
import org.devconmyanmar.devconyangon.domain.mapper.UnidirectionalMap
import org.devconmyanmar.devconyangon.domain.model.Session
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.feature.agenda.session.MyAgendaSessionViewItem.MyAgendaSessionViewItemHeader
import org.devconmyanmar.devconyangon.feature.agenda.session.MyAgendaSessionViewItem.MyAgendaSessionViewItemSession
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

/**
 * Created by Vincent on 11/6/19
 */
sealed class MyAgendaSessionViewItem {

  data class MyAgendaSessionViewItemHeader(
    val time: LocalTime,
    val timeInString: String
  ) : MyAgendaSessionViewItem()

  data class MyAgendaSessionViewItemSession(
    val sessionId: SessionId,
    val sessionTitle: String,
    val roomName: String,
    val speakerNames: String,
    val isFavorite: Boolean
  ) : MyAgendaSessionViewItem()

}

class MyAgendaSessionViewItemMapper @Inject constructor() :
  UnidirectionalMap<List<Session>, List<MyAgendaSessionViewItem>> {
  private val timeFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH)

  private val timeSet = mutableSetOf<LocalTime>()

  override fun map(item: List<Session>): List<MyAgendaSessionViewItem> {
    val listViewItems = mutableListOf<MyAgendaSessionViewItem>()
    val sortedSession = item.sortedBy {
      it.date.atTime(it.startTime)
    }
    timeSet.clear()
    sortedSession.forEach {
      val time = it.startTime
      val shouldAddHeader = timeSet.add(time)
      if (shouldAddHeader) {
        val headerItem = MyAgendaSessionViewItemHeader(
          time = time,
          timeInString = time.format(timeFormatter)
        )
        listViewItems.add(headerItem)
      }

      val sessionItem = MyAgendaSessionViewItemSession(
        sessionId = it.sessionId,
        sessionTitle = it.sessionTitle,
        isFavorite = it.isFavorite,
        roomName = it.room.roomName,
        speakerNames = if (it.speakers.isEmpty()) {
          "-"
        } else {
          it.speakers.map { it.name }.asDelimitedString(',')
        }
      )

      listViewItems.add(sessionItem)
    }
    return listViewItems.toList()
  }

}

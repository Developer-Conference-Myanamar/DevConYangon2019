package org.devconmyanmar.devconyangon.feature.agenda

import org.devconmyanmar.devconyangon.domain.helper.asDelimitedString
import org.devconmyanmar.devconyangon.domain.mapper.UnidirectionalMap
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.model.SessionListing
import org.devconmyanmar.devconyangon.feature.agenda.MyAgendaListViewItem.MyAgendaListViewItemHeader
import org.devconmyanmar.devconyangon.feature.agenda.MyAgendaListViewItem.MyAgendaListViewItemSession
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

/**
 * Created by Vincent on 11/5/19
 */
data class MyAgendaViewItem(
  val date: LocalDate,
  val dateAsString: String,
  val listItems: List<MyAgendaListViewItem>
)

sealed class MyAgendaListViewItem {

  data class MyAgendaListViewItemHeader(
    val time: LocalTime,
    val timeInString: String
  ) : MyAgendaListViewItem()

  data class MyAgendaListViewItemSession(
    val sessionId: SessionId,
    val sessionTitle: String,
    val roomName: String,
    val speakerNames: String,
    val isFavorite: Boolean
  ) : MyAgendaListViewItem()
}

class MyAgendaViewItemMapper @Inject constructor() :
  UnidirectionalMap<MyAgendaViewItemMapper.Params, MyAgendaViewItem> {

  private val dateFormatter = DateTimeFormatter.ofPattern("dd MMM", Locale.ENGLISH)
  private val timeFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH)

  private val timeSet = mutableSetOf<LocalTime>()

  override fun map(item: Params): MyAgendaViewItem {
    val listViewItems = mutableListOf<MyAgendaListViewItem>()
    val sortedSession = item.session.sortedBy {
      it.dateTime
    }
    timeSet.clear()
    sortedSession.forEach {
      val time = it.dateTime.toLocalTime()
      val shouldAddHeader = timeSet.add(time)
      if (shouldAddHeader) {
        val headerItem = MyAgendaListViewItemHeader(
          time = time,
          timeInString = time.format(timeFormatter)
        )
        listViewItems.add(headerItem)
      }

      val sessionItem = MyAgendaListViewItemSession(
        sessionId = it.sessionId,
        sessionTitle = it.sessionTitle,
        isFavorite = it.isFavorite,
        roomName = it.room.roomName,
        speakerNames = it.speakers.map { it.name }.asDelimitedString(',')
      )

      listViewItems.add(sessionItem)
    }

    return MyAgendaViewItem(
      date = item.localDate,
      dateAsString = item.localDate.format(dateFormatter),
      listItems = listViewItems
    )
  }

  data class Params(
    val localDate: LocalDate,
    val session: List<SessionListing>
  )

}
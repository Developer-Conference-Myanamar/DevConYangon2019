package org.devconmyanmar.devconyangon.feature.agenda

import org.devconmyanmar.devconyangon.domain.mapper.UnidirectionalMap
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

/**
 * Created by Vincent on 11/5/19
 */
data class MyAgendaViewItem(
  val date: LocalDate,
  val dateAsString: String
)

class MyAgendaViewItemMapper @Inject constructor() :
  UnidirectionalMap<LocalDate, MyAgendaViewItem> {

  private val dateFormatter = DateTimeFormatter.ofPattern("dd MMM", Locale.ENGLISH)

  override fun map(item: LocalDate): MyAgendaViewItem {
    return MyAgendaViewItem(
      date = item,
      dateAsString = item.format(dateFormatter)
    )
  }

}
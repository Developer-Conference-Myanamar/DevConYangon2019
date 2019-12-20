package org.devconmyanmar.devconyangon.feature.schedule

import org.devconmyanmar.devconyangon.domain.mapper.BidirectionalMap
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.Locale

/**
 * Created by Vincent on 2019-11-02
 */
data class ScheduleDateViewItem(
  val date: LocalDate,
  val dateAsString: String
) {

  object Mapper : BidirectionalMap<LocalDate, ScheduleDateViewItem> {

    private val dateFormatter = DateTimeFormatter.ofPattern("dd MMM", Locale.ENGLISH)

    override fun map(item: LocalDate): ScheduleDateViewItem {
      return ScheduleDateViewItem(
        date = item,
        dateAsString = item.format(dateFormatter)
      )
    }

    override fun reverseMap(item: ScheduleDateViewItem): LocalDate {
      return item.date
    }

  }
}
package org.devconmyanmar.devconyangon.feature.agenda

import org.devconmyanmar.devconyangon.feature.agenda.session.MyAgendaSessionViewItem
import org.devconmyanmar.devconyangon.feature.agenda.session.MyAgendaSessionViewItem.MyAgendaSessionViewItemHeader
import org.threeten.bp.Clock
import org.threeten.bp.ZonedDateTime
import javax.inject.Inject

/**
 * Created by Vincent on 11/5/19
 */
class FirstTimeListPositionFinder @Inject constructor(private val clock: Clock) {

  companion object {
    private const val NO_POSITION = -1
  }

  fun findDateIndexToScrollTo(viewItemList: List<MyAgendaDateViewItem>): Int {
    val currentDate = ZonedDateTime.now(clock).toLocalDate()
    var indexToScrollTo = NO_POSITION
    viewItemList.forEachIndexed { index, myAgendaDateViewItem ->
      if (myAgendaDateViewItem.date == currentDate) {
        indexToScrollTo = index
      }
    }
    return indexToScrollTo
  }

  /**
   * Assume the header is already sorted in ascending order
   */
  fun findTimeIndexToScrollTo(viewItemList: List<MyAgendaSessionViewItem>): Int {

    val currentDateTime = ZonedDateTime.now(clock)
    var indexToScrollTo = NO_POSITION
    val currentTime = currentDateTime.toLocalTime()

    val headerItemsMutableList = mutableListOf<MyAgendaSessionViewItemHeader>()

    viewItemList.forEach {
      if (it is MyAgendaSessionViewItemHeader) {
        headerItemsMutableList.add(it)
      }
    }
    val headerItemList = headerItemsMutableList.toList()

    headerItemList.toList().forEachIndexed { index, headerItem ->
      if (headerItem.time <= currentTime) {
        val itemAtNextIndex = headerItemList.getOrNull(index + 1)

        if (itemAtNextIndex != null && itemAtNextIndex.time >= currentTime) {
          indexToScrollTo = viewItemList.indexOf(headerItem)
        } else if (itemAtNextIndex == null) {
          indexToScrollTo = viewItemList.indexOf(headerItem)
        }
      }
    }
    return indexToScrollTo

  }
}
//package org.devconmyanmar.devconyangon.feature.agenda
//
//import org.devconmyanmar.devconyangon.feature.agenda.MyAgendaListViewItem.MyAgendaListViewItemHeader
//import org.threeten.bp.Clock
//import org.threeten.bp.LocalTime
//import org.threeten.bp.ZonedDateTime
//import javax.inject.Inject
//
///**
// * Created by Vincent on 11/5/19
// */
//class FirstTimeListPositionFinder @Inject constructor(private val clock: Clock) {
//
//  companion object {
//    private const val NO_POSITION = -1
//  }
//
//  /**
//   * Assume the header is already sorted in ascending order
//   */
//  fun findIndexToScrollTo(viewItemList: List<MyAgendaViewItem>): Pair<Int, Int> {
//
//    val currentDateTime = ZonedDateTime.now(clock)
//
//    var firstListIndexToScrollTo = NO_POSITION
//    var secondListIndexToScrollTo = NO_POSITION
//    val currentDate = currentDateTime.toLocalDate()
//    viewItemList.forEachIndexed { index, myAgendaViewItem ->
//      if (myAgendaViewItem.date == currentDate) {
//        firstListIndexToScrollTo = index
//      }
//
//      val currentTime = currentDateTime.toLocalTime()
//
//      val headerTimeWithIndexSet = mutableSetOf<Pair<LocalTime, Int>>()
//      myAgendaViewItem.listItems.forEachIndexed { index, myAgendaListViewItem ->
//        if (myAgendaListViewItem is MyAgendaListViewItemHeader) {
//          headerTimeWithIndexSet.add(Pair(myAgendaListViewItem.time, index))
//        }
//      }
//
//      headerTimeWithIndexSet.toSet().forEachIndexed { index, pair ->
//        if (pair.first <= currentTime) {
//          val itemAtNextIndex = headerTimeWithIndexSet.elementAtOrNull(index + 1)
//
//          if (itemAtNextIndex != null && itemAtNextIndex.first >= currentTime) {
//            secondListIndexToScrollTo = pair.second
//          } else if (itemAtNextIndex == null) {
//            secondListIndexToScrollTo = pair.second
//          }
//
//        }
//      }
//    }
//
//    return Pair(firstListIndexToScrollTo, secondListIndexToScrollTo)
//  }
//}
package org.devconmyanmar.devconyangon.feature.agenda

import org.devconmyanmar.devconyangon.domain.helper.Zones
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.feature.agenda.session.MyAgendaSessionViewItem.MyAgendaSessionViewItemHeader
import org.devconmyanmar.devconyangon.feature.agenda.session.MyAgendaSessionViewItem.MyAgendaSessionViewItemSession
import org.junit.Assert
import org.junit.Test
import org.threeten.bp.Clock
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import java.util.UUID
import kotlin.random.Random

/**
 * Created by Vincent on 11/5/19
 */
class FirstTimeListPositionFinderTest {

  private fun randomSessionItem(): MyAgendaSessionViewItemSession {
    return MyAgendaSessionViewItemSession(
      sessionId = SessionId(Random.nextLong()),
      sessionTitle = UUID.randomUUID().toString(),
      speakerNames = UUID.randomUUID().toString(),
      roomName = UUID.randomUUID().toString(),
      isFavorite = Random.nextBoolean()
    )
  }

  @Test
  fun testWithOneDate() {
    val viewItemList = listOf(
      MyAgendaDateViewItem(
        date = LocalDate.of(2018, 5, 20),
        dateAsString = "not important "
      )
    )
    val expected = 0
    val fixedInstant = LocalDateTime.of(2018, 5, 20, 9, 15).atZone(Zones.YANGON).toInstant()
    val clock = Clock.fixed(fixedInstant, Zones.YANGON)

    val actual = FirstTimeListPositionFinder(clock).findDateIndexToScrollTo(viewItemList)

    Assert.assertEquals(expected, actual)
  }

  @Test
  fun testWithTwoDate() {
    val viewItemList = listOf(
      MyAgendaDateViewItem(
        date = LocalDate.of(2018, 5, 20),
        dateAsString = "not important "
      ),
      MyAgendaDateViewItem(
        date = LocalDate.of(2018, 5, 21),
        dateAsString = "not important "
      )
    )
    val expected = 1
    val fixedInstant = LocalDateTime.of(2018, 5, 21, 9, 15).atZone(Zones.YANGON).toInstant()
    val clock = Clock.fixed(fixedInstant, Zones.YANGON)

    val actual = FirstTimeListPositionFinder(clock).findDateIndexToScrollTo(viewItemList)
    Assert.assertEquals(expected, actual)
  }

  @Test
  fun testWithThreeDate() {
    val viewItemList = listOf(
      MyAgendaDateViewItem(
        date = LocalDate.of(2018, 5, 20),
        dateAsString = "not important "
      ),
      MyAgendaDateViewItem(
        date = LocalDate.of(2018, 5, 21),
        dateAsString = "not important "
      ),
      MyAgendaDateViewItem(
        date = LocalDate.of(2018, 5, 22),
        dateAsString = "not important "
      )
    )
    val expected = 1
    val fixedInstant = LocalDateTime.of(2018, 5, 21, 9, 15).atZone(Zones.YANGON).toInstant()
    val clock = Clock.fixed(fixedInstant, Zones.YANGON)

    val actual = FirstTimeListPositionFinder(clock).findDateIndexToScrollTo(viewItemList)
    Assert.assertEquals(expected, actual)
  }

  @Test
  fun testWithThreeDateNotIncluded() {
    val viewItemList = listOf(
      MyAgendaDateViewItem(
        date = LocalDate.of(2018, 5, 20),
        dateAsString = "not important "
      ),
      MyAgendaDateViewItem(
        date = LocalDate.of(2018, 5, 21),
        dateAsString = "not important "
      ),
      MyAgendaDateViewItem(
        date = LocalDate.of(2018, 5, 22),
        dateAsString = "not important "
      )
    )
    val expected = -1
    val fixedInstant = LocalDateTime.of(2018, 5, 1, 9, 15).atZone(Zones.YANGON).toInstant()
    val clock = Clock.fixed(fixedInstant, Zones.YANGON)

    val actual = FirstTimeListPositionFinder(clock).findDateIndexToScrollTo(viewItemList)
    Assert.assertEquals(expected, actual)
  }

  @Test
  fun testWithOneTime() {
    val viewItemList = listOf(
      MyAgendaSessionViewItemHeader(
        time = LocalTime.of(9, 0),
        timeInString = "not important "
      ),
      randomSessionItem(),
      randomSessionItem()
    )
    val expected = 0
    val fixedInstant = LocalDateTime.of(2018, 5, 1, 9, 0).atZone(Zones.YANGON).toInstant()
    val clock = Clock.fixed(fixedInstant, Zones.YANGON)

    val actual = FirstTimeListPositionFinder(clock).findTimeIndexToScrollTo(viewItemList)
    Assert.assertEquals(expected, actual)
  }

  @Test
  fun testWithTwoTime() {
    val viewItemList = listOf(
      MyAgendaSessionViewItemHeader(
        time = LocalTime.of(9, 0),
        timeInString = "not important "
      ),
      randomSessionItem(),
      randomSessionItem(),
      MyAgendaSessionViewItemHeader(
        time = LocalTime.of(10, 30),
        timeInString = "not important "
      ),
      randomSessionItem()
    )
    val expected = 3
    val fixedInstant = LocalDateTime.of(2018, 5, 1, 10, 30).atZone(Zones.YANGON).toInstant()
    val clock = Clock.fixed(fixedInstant, Zones.YANGON)

    val actual = FirstTimeListPositionFinder(clock).findTimeIndexToScrollTo(viewItemList)
    Assert.assertEquals(expected, actual)
  }

  @Test
  fun testWithThreeTime() {
    val viewItemList = listOf(
      MyAgendaSessionViewItemHeader(
        time = LocalTime.of(9, 0),
        timeInString = "not important "
      ),
      randomSessionItem(),
      randomSessionItem(),
      MyAgendaSessionViewItemHeader(
        time = LocalTime.of(10, 30),
        timeInString = "not important "
      ),
      randomSessionItem(),
      MyAgendaSessionViewItemHeader(
        time = LocalTime.of(13, 0),
        timeInString = "not important "
      ),
      randomSessionItem(),
      randomSessionItem()
    )
    val expected = 3
    val fixedInstant = LocalDateTime.of(2018, 5, 1, 11, 0).atZone(Zones.YANGON).toInstant()
    val clock = Clock.fixed(fixedInstant, Zones.YANGON)

    val actual = FirstTimeListPositionFinder(clock).findTimeIndexToScrollTo(viewItemList)
    Assert.assertEquals(expected, actual)
  }

  @Test
  fun testThreeTimeButNotIncluded() {
    val viewItemList = listOf(
      MyAgendaSessionViewItemHeader(
        time = LocalTime.of(9, 0),
        timeInString = "not important "
      ),
      randomSessionItem(),
      randomSessionItem(),
      MyAgendaSessionViewItemHeader(
        time = LocalTime.of(10, 30),
        timeInString = "not important "
      ),
      randomSessionItem(),
      MyAgendaSessionViewItemHeader(
        time = LocalTime.of(13, 0),
        timeInString = "not important "
      ),
      randomSessionItem(),
      randomSessionItem()
    )
    val expected = -1
    val fixedInstant = LocalDateTime.of(2018, 5, 1, 8, 0).atZone(Zones.YANGON).toInstant()
    val clock = Clock.fixed(fixedInstant, Zones.YANGON)

    val actual = FirstTimeListPositionFinder(clock).findTimeIndexToScrollTo(viewItemList)
    Assert.assertEquals(expected, actual)
  }
//
//  @Test
//  fun testWithOneItemWithOneItemEach() {
//    //Given
//    val viewItemList = listOf(
//      MyAgendaViewItem(
//        date = LocalDate.of(2018, 5, 20),
//        dateAsString = "not important ",
//        listItems = listOf(
//          MyAgendaListViewItemHeader(
//            time = LocalTime.of(9, 0),
//            timeInString = "la la la"
//          ),
//          randomSessionItem(),
//          randomSessionItem()
//        )
//      )
//    )
//
//    val expected = Pair(0, 0)
//
//    val fixedInstant = LocalDateTime.of(2018, 5, 20, 9, 15).atZone(Zones.YANGON).toInstant()
//    val clock = Clock.fixed(fixedInstant, Zones.YANGON)
//
//    //When
//    val actual = FirstTimeListPositionFinder(clock).findIndexToScrollTo(viewItemList)
//
//    //Then
//    Assert.assertEquals(actual.first, expected.first)
//    Assert.assertEquals(actual.second, expected.second)
//  }
//
//  @Test
//  fun testWithTwoItemWithOneItemEach() {
//    //Given
//    val viewItemList = listOf(
//      MyAgendaViewItem(
//        date = LocalDate.of(2018, 5, 20),
//        dateAsString = "not important ",
//        listItems = listOf(
//          MyAgendaListViewItemHeader(
//            time = LocalTime.of(9, 0),
//            timeInString = "la la la"
//          ),
//          randomSessionItem(),
//          randomSessionItem()
//        )
//      ),
//      MyAgendaViewItem(
//        date = LocalDate.of(2018, 5, 21),
//        dateAsString = "not important ",
//        listItems = listOf(
//          MyAgendaListViewItemHeader(
//            time = LocalTime.of(9, 0),
//            timeInString = "la la la"
//          ),
//          randomSessionItem(),
//          randomSessionItem()
//        )
//      )
//    )
//
//    val expected = Pair(1, 0)
//
//    val fixedInstant = LocalDateTime.of(2018, 5, 21, 9, 15).atZone(Zones.YANGON).toInstant()
//    val clock = Clock.fixed(fixedInstant, Zones.YANGON)
//
//    //When
//    val actual = FirstTimeListPositionFinder(clock).findIndexToScrollTo(viewItemList)
//
//    //Then
//    Assert.assertEquals(actual.first, expected.first)
//    Assert.assertEquals(actual.second, expected.second)
//  }
//
//  @Test
//  fun testWithOneItemWithThreeItems() {
//    //Given
//    val viewItemList = listOf(
//      MyAgendaViewItem(
//        date = LocalDate.of(2018, 5, 20),
//        dateAsString = "not important ",
//        listItems = listOf(
//          MyAgendaListViewItemHeader(
//            time = LocalTime.of(9, 0),
//            timeInString = "la la la"
//          ),
//          randomSessionItem(),
//          randomSessionItem(),
//          MyAgendaListViewItemHeader(
//            time = LocalTime.of(10, 30),
//            timeInString = "la la la"
//          ),
//          randomSessionItem(),
//          randomSessionItem(),
//          MyAgendaListViewItemHeader(
//            time = LocalTime.of(13, 0),
//            timeInString = "la la la"
//          ),
//          randomSessionItem(),
//          randomSessionItem()
//        )
//      )
//    )
//
//    val expected = Pair(0, 3)
//
//    val fixedInstant = LocalDateTime.of(2018, 5, 20, 11, 0).atZone(Zones.YANGON).toInstant()
//    val clock = Clock.fixed(fixedInstant, Zones.YANGON)
//
//    //When
//    val actual = FirstTimeListPositionFinder(clock).findIndexToScrollTo(viewItemList)
//
//    //Then
//    Assert.assertEquals(actual.first, expected.first)
//    Assert.assertEquals(actual.second, expected.second)
//  }
//
//  @Test
//  fun testWithOneItemWithThreeItemsCaseLastItem() {
//    //Given
//    val viewItemList = listOf(
//      MyAgendaViewItem(
//        date = LocalDate.of(2018, 5, 20),
//        dateAsString = "not important ",
//        listItems = listOf(
//          MyAgendaListViewItemHeader(
//            time = LocalTime.of(9, 0),
//            timeInString = "la la la"
//          ),
//          randomSessionItem(),
//          randomSessionItem(),
//          MyAgendaListViewItemHeader(
//            time = LocalTime.of(10, 30),
//            timeInString = "la la la"
//          ),
//          randomSessionItem(),
//          randomSessionItem(),
//          MyAgendaListViewItemHeader(
//            time = LocalTime.of(13, 0),
//            timeInString = "la la la"
//          ),
//          randomSessionItem(),
//          randomSessionItem()
//        )
//      )
//    )
//
//    val expected = Pair(0, 6)
//
//    val fixedInstant = LocalDateTime.of(2018, 5, 20, 14, 0).atZone(Zones.YANGON).toInstant()
//    val clock = Clock.fixed(fixedInstant, Zones.YANGON)
//
//    //When
//    val actual = FirstTimeListPositionFinder(clock).findIndexToScrollTo(viewItemList)
//
//    //Then
//    Assert.assertEquals(actual.first, expected.first)
//    Assert.assertEquals(actual.second, expected.second)
//  }
//
//  @Test
//  fun testWithOneItemThatIsNotAtDate() {
//    //Given
//    val viewItemList = listOf(
//      MyAgendaViewItem(
//        date = LocalDate.of(2018, 5, 20),
//        dateAsString = "not important ",
//        listItems = listOf()
//      )
//    )
//
//    val expected = Pair(-1, -1)
//
//    val fixedInstant = LocalDateTime.of(2018, 5, 19, 0, 0).atZone(Zones.YANGON).toInstant()
//    val clock = Clock.fixed(fixedInstant, Zones.YANGON)
//
//    //When
//    val actual = FirstTimeListPositionFinder(clock).findIndexToScrollTo(viewItemList)
//
//    //Then
//    Assert.assertEquals(actual.first, expected.first)
//    Assert.assertEquals(actual.second, expected.second)
//  }
//
//  @Test
//  fun testWithOneItemWithEmptyItemInside() {
//    //Given
//    val viewItemList = listOf(
//      MyAgendaViewItem(
//        date = LocalDate.of(2018, 5, 20),
//        dateAsString = "not important ",
//        listItems = listOf()
//      )
//    )
//
//    val expected = Pair(0, -1)
//
//    val fixedInstant = LocalDateTime.of(2018, 5, 20, 9, 0).atZone(Zones.YANGON).toInstant()
//    val clock = Clock.fixed(fixedInstant, Zones.YANGON)
//
//    //When
//    val actual = FirstTimeListPositionFinder(clock).findIndexToScrollTo(viewItemList)
//
//    //Then
//    Assert.assertEquals(actual.first, expected.first)
//    Assert.assertEquals(actual.second, expected.second)
//  }

}

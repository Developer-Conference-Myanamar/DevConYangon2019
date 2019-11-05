package org.devconmyanmar.devconyangon.feature.agenda

import org.devconmyanmar.devconyangon.domain.helper.Zones
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.feature.agenda.MyAgendaListViewItem.MyAgendaListViewItemHeader
import org.devconmyanmar.devconyangon.feature.agenda.MyAgendaListViewItem.MyAgendaListViewItemSession
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

  private fun randomSessionItem(): MyAgendaListViewItemSession {
    return MyAgendaListViewItemSession(
      sessionId = SessionId(Random.nextLong()),
      sessionTitle = UUID.randomUUID().toString(),
      speakerNames = UUID.randomUUID().toString(),
      roomName = UUID.randomUUID().toString(),
      isFavorite = Random.nextBoolean()
    )
  }

  @Test
  fun testWithOneItemWithOneItemEach() {
    //Given
    val viewItemList = listOf(
      MyAgendaViewItem(
        date = LocalDate.of(2018, 5, 20),
        dateAsString = "not important ",
        listItems = listOf(
          MyAgendaListViewItemHeader(
            time = LocalTime.of(9, 0),
            timeInString = "la la la"
          ),
          randomSessionItem(),
          randomSessionItem()
        )
      )
    )

    val expected = Pair(0, 0)

    val fixedInstant = LocalDateTime.of(2018, 5, 20, 9, 15).atZone(Zones.YANGON).toInstant()
    val clock = Clock.fixed(fixedInstant, Zones.YANGON)

    //When
    val actual = FirstTimeListPositionFinder(clock).findIndexToScrollTo(viewItemList)

    //Then
    Assert.assertEquals(actual.first, expected.first)
    Assert.assertEquals(actual.second, expected.second)
  }

  @Test
  fun testWithTwoItemWithOneItemEach() {
    //Given
    val viewItemList = listOf(
      MyAgendaViewItem(
        date = LocalDate.of(2018, 5, 20),
        dateAsString = "not important ",
        listItems = listOf(
          MyAgendaListViewItemHeader(
            time = LocalTime.of(9, 0),
            timeInString = "la la la"
          ),
          randomSessionItem(),
          randomSessionItem()
        )
      ),
      MyAgendaViewItem(
        date = LocalDate.of(2018, 5, 21),
        dateAsString = "not important ",
        listItems = listOf(
          MyAgendaListViewItemHeader(
            time = LocalTime.of(9, 0),
            timeInString = "la la la"
          ),
          randomSessionItem(),
          randomSessionItem()
        )
      )
    )

    val expected = Pair(1, 0)

    val fixedInstant = LocalDateTime.of(2018, 5, 21, 9, 15).atZone(Zones.YANGON).toInstant()
    val clock = Clock.fixed(fixedInstant, Zones.YANGON)

    //When
    val actual = FirstTimeListPositionFinder(clock).findIndexToScrollTo(viewItemList)

    //Then
    Assert.assertEquals(actual.first, expected.first)
    Assert.assertEquals(actual.second, expected.second)
  }

  @Test
  fun testWithOneItemWithThreeItems() {
    //Given
    val viewItemList = listOf(
      MyAgendaViewItem(
        date = LocalDate.of(2018, 5, 20),
        dateAsString = "not important ",
        listItems = listOf(
          MyAgendaListViewItemHeader(
            time = LocalTime.of(9, 0),
            timeInString = "la la la"
          ),
          randomSessionItem(),
          randomSessionItem(),
          MyAgendaListViewItemHeader(
            time = LocalTime.of(10, 30),
            timeInString = "la la la"
          ),
          randomSessionItem(),
          randomSessionItem(),
          MyAgendaListViewItemHeader(
            time = LocalTime.of(13, 0),
            timeInString = "la la la"
          ),
          randomSessionItem(),
          randomSessionItem()
        )
      )
    )

    val expected = Pair(0, 3)

    val fixedInstant = LocalDateTime.of(2018, 5, 20, 11, 0).atZone(Zones.YANGON).toInstant()
    val clock = Clock.fixed(fixedInstant, Zones.YANGON)

    //When
    val actual = FirstTimeListPositionFinder(clock).findIndexToScrollTo(viewItemList)

    //Then
    Assert.assertEquals(actual.first, expected.first)
    Assert.assertEquals(actual.second, expected.second)
  }

  @Test
  fun testWithOneItemWithThreeItemsCaseLastItem() {
    //Given
    val viewItemList = listOf(
      MyAgendaViewItem(
        date = LocalDate.of(2018, 5, 20),
        dateAsString = "not important ",
        listItems = listOf(
          MyAgendaListViewItemHeader(
            time = LocalTime.of(9, 0),
            timeInString = "la la la"
          ),
          randomSessionItem(),
          randomSessionItem(),
          MyAgendaListViewItemHeader(
            time = LocalTime.of(10, 30),
            timeInString = "la la la"
          ),
          randomSessionItem(),
          randomSessionItem(),
          MyAgendaListViewItemHeader(
            time = LocalTime.of(13, 0),
            timeInString = "la la la"
          ),
          randomSessionItem(),
          randomSessionItem()
        )
      )
    )

    val expected = Pair(0, 6)

    val fixedInstant = LocalDateTime.of(2018, 5, 20, 14, 0).atZone(Zones.YANGON).toInstant()
    val clock = Clock.fixed(fixedInstant, Zones.YANGON)

    //When
    val actual = FirstTimeListPositionFinder(clock).findIndexToScrollTo(viewItemList)

    //Then
    Assert.assertEquals(actual.first, expected.first)
    Assert.assertEquals(actual.second, expected.second)
  }

  @Test
  fun testWithOneItemThatIsNotAtDate() {
    //Given
    val viewItemList = listOf(
      MyAgendaViewItem(
        date = LocalDate.of(2018, 5, 20),
        dateAsString = "not important ",
        listItems = listOf()
      )
    )

    val expected = Pair(-1, -1)

    val fixedInstant = LocalDateTime.of(2018, 5, 19, 0, 0).atZone(Zones.YANGON).toInstant()
    val clock = Clock.fixed(fixedInstant, Zones.YANGON)

    //When
    val actual = FirstTimeListPositionFinder(clock).findIndexToScrollTo(viewItemList)

    //Then
    Assert.assertEquals(actual.first, expected.first)
    Assert.assertEquals(actual.second, expected.second)
  }

  @Test
  fun testWithOneItemWithEmptyItemInside() {
    //Given
    val viewItemList = listOf(
      MyAgendaViewItem(
        date = LocalDate.of(2018, 5, 20),
        dateAsString = "not important ",
        listItems = listOf()
      )
    )

    val expected = Pair(0, -1)

    val fixedInstant = LocalDateTime.of(2018, 5, 20, 9, 0).atZone(Zones.YANGON).toInstant()
    val clock = Clock.fixed(fixedInstant, Zones.YANGON)

    //When
    val actual = FirstTimeListPositionFinder(clock).findIndexToScrollTo(viewItemList)

    //Then
    Assert.assertEquals(actual.first, expected.first)
    Assert.assertEquals(actual.second, expected.second)
  }

}

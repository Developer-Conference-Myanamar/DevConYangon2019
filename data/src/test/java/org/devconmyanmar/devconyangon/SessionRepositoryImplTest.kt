package org.devconmyanmar.devconyangon

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.runBlocking
import org.devconmyanmar.devconyangon.data.SessionRepositoryRealImpl
import org.devconmyanmar.devconyangon.data.datasource.SessionCacheDataSource
import org.devconmyanmar.devconyangon.data.datasource.SessionNetworkDataSource
import org.devconmyanmar.devconyangon.data.entity.RoomEntity
import org.devconmyanmar.devconyangon.data.entity.RoomEntityMapper
import org.devconmyanmar.devconyangon.data.entity.SessionEntity
import org.devconmyanmar.devconyangon.data.entity.SessionEntityToListingMapper
import org.devconmyanmar.devconyangon.data.entity.SpeakerEntity
import org.devconmyanmar.devconyangon.data.entity.SpeakerEntityMapper
import org.devconmyanmar.devconyangon.data.exception.EmptyDataException
import org.devconmyanmar.devconyangon.domain.helper.Zones
import org.devconmyanmar.devconyangon.domain.model.RoomId
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.model.SpeakerId
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.threeten.bp.Clock
import org.threeten.bp.LocalDate
import org.threeten.bp.ZonedDateTime
import java.io.IOException

/**
 * Created by Vincent on 11/3/19
 */
class SessionRepositoryImplTest {

  @Mock
  lateinit var sessionNetworkDataSource: SessionNetworkDataSource
  @Mock
  lateinit var sessionCacheDataSource: SessionCacheDataSource

  private val sessionEntityToListingMapper = SessionEntityToListingMapper(
    RoomEntityMapper(),
    SpeakerEntityMapper(),
    Clock.system(Zones.YANGON)
  )

  lateinit var sessionRepository: SessionRepositoryRealImpl

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this);
    sessionRepository = SessionRepositoryRealImpl(
      sessionNetworkDataSource,
      sessionCacheDataSource,
      sessionEntityToListingMapper
    )
  }

  @Test
  fun testNetworkSuccess() {

    //Given
    val fakeSessionEntity = SessionEntity(
      sessionId = SessionId(0),
      sessionTitle = "Building a robust web System",
      dateTimeInInstant = ZonedDateTime.of(2019, 12, 21, 9, 0, 0, 0, Zones.YANGON).toInstant(),
      room = RoomEntity(
        roomId = RoomId(0),
        roomName = "Main Hall"
      ),
      speakers = listOf(
        SpeakerEntity(
          speakerId = SpeakerId(0),
          name = "Fake Wharton"
        )
      )
    )

    whenever(sessionNetworkDataSource.getSessions(any())).thenReturn(
      listOf()
    )
    whenever(sessionCacheDataSource.getSessionEntities(any())).thenReturn(listOf(fakeSessionEntity))
    whenever(sessionCacheDataSource.putSessionEntities(any())).then {

    }

    //When
    val actual = runBlocking {
      sessionRepository.getSessionListing(LocalDate.now())
    }

    val expected = listOf(sessionEntityToListingMapper.map(fakeSessionEntity))

    //Then
    Assert.assertEquals(expected, actual)
    verify(sessionCacheDataSource, times(1)).getSessionEntities(any())
    verify(sessionCacheDataSource, times(1)).putSessionEntities(any())
    verify(sessionCacheDataSource, times(1)).getSessionEntities(any())
  }

  @Test
  fun testNetworkFailButCacheHasData() {

    //Given
    val fakeSessionEntity = SessionEntity(
      sessionId = SessionId(0),
      sessionTitle = "Building a robust web System",
      dateTimeInInstant = ZonedDateTime.of(2019, 12, 21, 9, 0, 0, 0, Zones.YANGON).toInstant(),
      room = RoomEntity(
        roomId = RoomId(0),
        roomName = "Main Hall"
      ),
      speakers = listOf(
        SpeakerEntity(
          speakerId = SpeakerId(0),
          name = "Fake Wharton"
        )
      )
    )

    whenever(sessionNetworkDataSource.getSessions(any())).thenAnswer {
      throw IOException()
    }
    whenever(sessionCacheDataSource.getSessionEntities(any())).thenReturn(listOf(fakeSessionEntity))
    whenever(sessionCacheDataSource.putSessionEntities(any())).then {

    }
    //When
    val actual = runBlocking {
      sessionRepository.getSessionListing(LocalDate.now())
    }

    val expected = listOf(sessionEntityToListingMapper.map(fakeSessionEntity))

    //Then
    Assert.assertEquals(expected, actual)
    verify(sessionCacheDataSource, times(1)).getSessionEntities(any())
    verify(sessionCacheDataSource, times(0)).putSessionEntities(any())
    verify(sessionCacheDataSource, times(1)).getSessionEntities(any())
  }

  @Test(expected = IOException::class)
  fun testNetworkFailButCacheHasNoData() {

    //Given
    whenever(sessionNetworkDataSource.getSessions(any())).thenAnswer {
      throw IOException()
    }
    whenever(sessionCacheDataSource.getSessionEntities(any())).thenReturn(listOf())
    whenever(sessionCacheDataSource.putSessionEntities(any())).then {

    }
    //When
    runBlocking {
      sessionRepository.getSessionListing(LocalDate.now())
    }
    //Then
    verify(sessionCacheDataSource, times(1)).getSessionEntities(any())
    verify(sessionCacheDataSource, times(0)).putSessionEntities(any())
    verify(sessionCacheDataSource, times(1)).getSessionEntities(any())
  }

  @Test(expected = EmptyDataException::class)
  fun testNetworkSuccessButEmptyData() {

    //Given
    whenever(sessionNetworkDataSource.getSessions(any())).thenReturn(listOf())
    whenever(sessionCacheDataSource.getSessionEntities(any())).thenReturn(listOf())
    whenever(sessionCacheDataSource.putSessionEntities(any())).then {

    }
    //When
    runBlocking {
      sessionRepository.getSessionListing(LocalDate.now())
    }
    //Then
    verify(sessionCacheDataSource, times(1)).getSessionEntities(any())
    verify(sessionCacheDataSource, times(1)).putSessionEntities(any())
    verify(sessionCacheDataSource, times(1)).getSessionEntities(any())
  }
}
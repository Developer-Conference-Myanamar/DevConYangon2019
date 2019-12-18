package org.devconmyanmar.devconyangon.network

import org.devconmyanmar.devconyangon.data.datasource.NetworkDataSource
import org.devconmyanmar.devconyangon.data.entity.RoomEntity
import org.devconmyanmar.devconyangon.data.entity.SessionEntity
import org.devconmyanmar.devconyangon.data.entity.SpeakerEntity
import org.devconmyanmar.devconyangon.data.entity.SponsorEntity
import org.devconmyanmar.devconyangon.domain.model.RoomId
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.model.SpeakerId
import org.devconmyanmar.devconyangon.domain.model.SponsorId
import org.devconmyanmar.devconyangon.network.response.SponsorItem
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

/**
 * Created by Vincent on 12/14/19
 */

class NetworkDataSourceImpl @Inject constructor(
  private val devconYangonApi: DevconYangonApi
) : NetworkDataSource {

  override fun getAllSpeakers(): List<SpeakerEntity> {
    val response = devconYangonApi.getSchedules().executeOrThrow()

    val speakerAndSessionIdMap = mutableMapOf<SpeakerId, Set<SessionId>>()

    response.schedules.forEachIndexed { index, schedule ->

      val sessionId = SessionId(schedule.scheduleId)
      schedule.speaker.forEach {

        val speakerId = SpeakerId(it.speakerId)
        //Check if exist already
        speakerAndSessionIdMap[speakerId] = if (speakerAndSessionIdMap.containsKey(speakerId)) {
          speakerAndSessionIdMap.getValue(speakerId) + sessionId
        } else {
          setOf(sessionId)
        }
      }
    }

    val speakerSet = mutableSetOf<SpeakerEntity>()
    response.schedules.forEach { schedule ->

      schedule.speaker.forEach {
        val speakerId = SpeakerId(it.speakerId)
        val speakerEntity = SpeakerEntity(
          speakerId = speakerId,
          name = it.name,
          biography = it.info,
          position = it.position,
          imageUrl = "",
          sessionList = speakerAndSessionIdMap.getValue(speakerId).toList()
        )
        speakerSet.add(speakerEntity)
      }
    }

    return speakerSet.toList()
  }

  override fun getAllSession(): List<SessionEntity> {
    val response = devconYangonApi.getSchedules().executeOrThrow()

    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH)

    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)

    return response.schedules.mapIndexed { index, schedule ->
      SessionEntity(
        sessionId = SessionId(schedule.scheduleId),
        sessionTitle = schedule.scheduleTitle,
        startTime = LocalTime.parse(schedule.scheduleStartTime, timeFormatter),
        endTime = LocalTime.parse(schedule.scheduleStartTime, timeFormatter).plusMinutes(45),
        room = RoomEntity(
          RoomId(index.toLong()),
          schedule.scheduleLocation
        ),
        abstract = schedule.scheduleDescription,
        date = LocalDate.parse(schedule.scheduleDate.substringBefore(' '), dateFormatter),
        speakers = schedule.speaker.map { SpeakerId(it.speakerId) }
      )

    }
  }

  override fun getAllSponsors(): List<SponsorEntity> {
    val response = devconYangonApi.getSponsors().executeOrThrow()
    return response.sponsor.mapIndexed { index: Int, sponsorItem: SponsorItem ->
      val sponsorId=SponsorId(sponsorItem.id.toLong())
      SponsorEntity(
        id = sponsorId,
        sponsorTitle = sponsorItem.sponsorTitle,
        sponserType = sponsorItem.sponserType,
        updatedAt = sponsorItem.updatedAt,
        createdAt = sponsorItem.createdAt,
        sponsorLogo = sponsorItem.sponsorLogo,
        sponsorName = sponsorItem.sponsorId,
        sponsorDetail = sponsorItem.sponsorDetail
      )
    }
  }

}
package org.devconmyanmar.devconyangon.network

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.Request
import org.devconmyanmar.devconyangon.data.datasource.NetworkDataSource
import org.devconmyanmar.devconyangon.data.entity.RoomEntity
import org.devconmyanmar.devconyangon.data.entity.SessionEntity
import org.devconmyanmar.devconyangon.data.entity.SpeakerEntity
import org.devconmyanmar.devconyangon.data.entity.SponsorEntity
import org.devconmyanmar.devconyangon.domain.model.RoomId
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.model.SpeakerId
import org.devconmyanmar.devconyangon.domain.model.SponsorId
import org.devconmyanmar.devconyangon.network.exception.NetworkException
import org.devconmyanmar.devconyangon.network.response.JsonFormat
import org.devconmyanmar.devconyangon.network.response.JsonFormatSpeaker
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

/**
 * Created by Vincent on 12/14/19
 */

class GithubNetworkDataSourceImpl @Inject constructor(
  private val devconYangonApi: DevconYangonApi,
  private val okHttpClient: OkHttpClient
) : NetworkDataSource {

  companion object {
   private const val FILE_URL = "https://raw.githubusercontent.com/vincent-paing/devcon/master/DevCon2k19.json"
  }

  private val moshi = Moshi.Builder().build()

  private val dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME

  override fun getAllSpeakers(): List<SpeakerEntity> {
    val request = Request.Builder()
      .url(FILE_URL)
      .get()
      .build()

    val response = okHttpClient.newCall(request).execute()

    if (response.isSuccessful.not()) {
      throw NetworkException(response.body, response.code)
    } else {
      val inputStream = response.body!!.byteStream()
      // input stream to read file - with 8k buffer
      val bufferedReader = inputStream.bufferedReader()

      var jsonContent: String
      try {
        jsonContent = bufferedReader.readText()
      } finally {
        bufferedReader.close()
        inputStream.close()
      }

      val jsonFormatAdapter = moshi.adapter(JsonFormat::class.java)

      val formFormat = jsonFormatAdapter.fromJson(jsonContent)!!

      val allSessions =
        formFormat.schedule.dayOneSessionList + formFormat.schedule.dayTwoSessionList

      val speakerSet = mutableSetOf<JsonFormatSpeaker>()

      allSessions.forEach {
        it.speakers.forEach {
          speakerSet.add(it)
        }
      }

      val sortedSpeakerList = speakerSet.sortedBy { it.name }

      return sortedSpeakerList.mapIndexed { index, speaker ->
        SpeakerEntity(
          speakerId = SpeakerId(index.toLong()),
          name = speaker.name,
          biography = "",
          position = speaker.position,
          imageUrl = speaker.image,
          sessionList = listOf()
        )
      }
    }
  }

  override fun getAllSession(): List<SessionEntity> {
    val request = Request.Builder()
      .url(FILE_URL)
      .get()
      .build()

    val response = okHttpClient.newCall(request).execute()

    if (response.isSuccessful.not()) {
      throw NetworkException(response.body, response.code)
    } else {
      val inputStream = response.body!!.byteStream()
      // input stream to read file - with 8k buffer
      val bufferedReader = inputStream.bufferedReader()

      var jsonContent: String
      try {
        jsonContent = bufferedReader.readText()
      } finally {
        bufferedReader.close()
        inputStream.close()
      }

      val jsonFormatAdapter = moshi.adapter(JsonFormat::class.java)

      val formFormat = jsonFormatAdapter.fromJson(jsonContent)!!

      val allSessions =
        formFormat.schedule.dayOneSessionList + formFormat.schedule.dayTwoSessionList

      val speakerSet = mutableSetOf<String>()

      allSessions.forEach {
        it.speakers.forEach {
          speakerSet.add(it.name)
        }
      }

      val sortedSpeakerSet = speakerSet.sorted()

      var roomId = 0L
      return allSessions.mapIndexed { index, session ->
        val sessionEntity = SessionEntity(
          sessionId = SessionId(index.toLong()),
          sessionTitle = session.topic,
          date = ZonedDateTime.parse(session.from, dateTimeFormatter).toLocalDate(),
          startTime = ZonedDateTime.parse(session.from, dateTimeFormatter).toLocalTime(),
          endTime = ZonedDateTime.parse(session.to, dateTimeFormatter).toLocalTime(),
          room = RoomEntity(
            roomId = RoomId(roomId),
            roomName = session.room
          ),
          speakers = session.speakers.map {
            SpeakerId(sortedSpeakerSet.indexOf(it.name).toLong())
          },
          abstract = ""
        )
        roomId++
        sessionEntity
      }
    }
  }

  override fun getAllSponsors(): List<SponsorEntity> {
    val response = devconYangonApi.getSponsors().executeOrThrow()

    return response.sponsorList.map {
      SponsorEntity(
        id = SponsorId(it.id),
        title = it.sponsorTitle,
        name = it.sponsorName,
        type = it.sponsorType,
        detail = it.sponsorDetail,
        logo = it.sponsorLogo
      )
    }
  }

}
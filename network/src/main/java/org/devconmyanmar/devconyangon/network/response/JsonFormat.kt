package org.devconmyanmar.devconyangon.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Vincent on 12/21/19
 */
@JsonClass(generateAdapter = true)
data class JsonFormat(
  @Json(name = "schedule") val schedule: JsonFormatSchedule
)

@JsonClass(generateAdapter = true)
data class JsonFormatSchedule(
  @Json(name = "day1") val dayOneSessionList: List<JsonFormatSession>,
  @Json(name = "day2") val dayTwoSessionList: List<JsonFormatSession>
)

@JsonClass(generateAdapter = true)
data class JsonFormatSession(
  @Json(name = "room") val room: String,
  @Json(name = "topic") val topic: String,
  @Json(name = "from") val from: String,
  @Json(name = "to") val to: String,
  @Json(name = "speakers") val speakers: List<JsonFormatSpeaker>
)

@JsonClass(generateAdapter = true)
data class JsonFormatSpeaker(
  @Json(name = "name") val name: String,
  @Json(name = "position") val position: String,
  @Json(name = "speaker_image") val image: String
)
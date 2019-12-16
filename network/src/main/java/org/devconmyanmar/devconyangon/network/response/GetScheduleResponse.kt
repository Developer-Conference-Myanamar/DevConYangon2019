package org.devconmyanmar.devconyangon.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Vincent on 12/16/19
 */
@JsonClass(generateAdapter = true)
data class GetScheduleResponse(
  @Json(name = "shedules") val schedules: List<GetScheduleResponseSchedule>
)

@JsonClass(generateAdapter = true)
data class GetScheduleResponseSchedule(
  @Json(name = "id") val scheduleId: Long,
  @Json(name = "schedule_time") val scheduleStartTime: String,
  @Json(name = "end_time") val scheduleEndTime: String,
  @Json(name = "schedule_date") val scheduleDate: String,
  @Json(name = "schedule_title") val scheduleTitle: String,
  @Json(name = "schedule_location") val scheduleLocation: String,
  @Json(name = "schedule_desc") val scheduleDescription: String,
  @Json(name = "speakers") val speaker: List<GetScheduleResponseScheduleSpeaker>
)

@JsonClass(generateAdapter = true)
data class GetScheduleResponseScheduleSpeaker(
  @Json(name = "id") val speakerId: Long,
  @Json(name = "name") val name: String,
  @Json(name = "position") val position: String,
  @Json(name = "info") val info: String

)
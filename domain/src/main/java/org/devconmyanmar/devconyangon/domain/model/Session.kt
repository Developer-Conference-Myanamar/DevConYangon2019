package org.devconmyanmar.devconyangon.domain.model

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime

/**
 * Created by Vincent on 2019-11-02
 */
data class Session(
  val sessionId: SessionId,
  val sessionTitle: String,
  val abstract: String,
  val date: LocalDate,
  val startTime: LocalTime,
  val endTime: LocalTime,
  val room: Room,
  val speakers: List<Speaker>,
  val isFavorite: Boolean
)

inline class SessionId(val value: Long)
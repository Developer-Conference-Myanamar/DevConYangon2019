package org.devconmyanmar.devconyangon.domain.model

import org.threeten.bp.ZonedDateTime

/**
 * Created by Vincent on 2019-11-02
 */
data class Session(
  val sessionId: SessionId,
  val sessionTitle: String,
  val dateTime: ZonedDateTime,
  val room: Room,
  val speakers: List<Speaker>
)

inline class SessionId(val value: Long)
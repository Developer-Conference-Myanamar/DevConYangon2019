package org.devconmyanmar.devconyangon.domain.model

/**
 * Created by Vincent on 2019-11-02
 */
data class Speaker(
  val speakerId: SpeakerId,
  val name: String
)

inline class SpeakerId(val value: Long)
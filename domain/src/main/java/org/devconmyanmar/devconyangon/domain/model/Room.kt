package org.devconmyanmar.devconyangon.domain.model

/**
 * Created by Vincent on 2019-11-02
 */
data class Room(
  val roomId: RoomId,
  val roomName: String
)

inline class RoomId(val value: Long)
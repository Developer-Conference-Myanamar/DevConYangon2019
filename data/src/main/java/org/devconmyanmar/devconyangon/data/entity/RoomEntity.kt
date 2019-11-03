package org.devconmyanmar.devconyangon.data.entity

import org.devconmyanmar.devconyangon.domain.mapper.UnidirectionalMap
import org.devconmyanmar.devconyangon.domain.model.Room
import org.devconmyanmar.devconyangon.domain.model.RoomId
import javax.inject.Inject

/**
 * Created by Vincent on 11/3/19
 */
data class RoomEntity(
  val roomId: RoomId,
  val roomName: String
)

class RoomEntityMapper @Inject constructor() : UnidirectionalMap<RoomEntity, Room> {

  override fun map(item: RoomEntity): Room {
    return Room(
      item.roomId,
      item.roomName
    )
  }

}
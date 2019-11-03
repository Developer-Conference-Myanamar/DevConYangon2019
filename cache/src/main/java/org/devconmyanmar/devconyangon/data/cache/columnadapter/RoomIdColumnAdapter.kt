package org.devconmyanmar.devconyangon.data.cache.columnadapter

import com.squareup.sqldelight.ColumnAdapter
import org.devconmyanmar.devconyangon.domain.model.RoomId

/**
 * Created by Vincent on 2019-10-10
 */
object RoomIdColumnAdapter : ColumnAdapter<RoomId, Long> {

  override fun decode(databaseValue: Long): RoomId {
    return RoomId(databaseValue)
  }

  override fun encode(value: RoomId): Long {
    return value.value
  }

}

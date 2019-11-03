package org.devconmyanmar.devconyangon.data.cache.columnadapter

import com.squareup.sqldelight.ColumnAdapter
import org.devconmyanmar.devconyangon.domain.model.SessionId

/**
 * Created by Vincent on 2019-10-10
 */
object SessionIdColumnAdapter : ColumnAdapter<SessionId, Long> {

  override fun decode(databaseValue: Long): SessionId {
    return SessionId(databaseValue)
  }

  override fun encode(value: SessionId): Long {
    return value.value
  }

}

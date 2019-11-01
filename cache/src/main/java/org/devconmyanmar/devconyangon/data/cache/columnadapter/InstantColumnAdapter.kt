package org.devconmyanmar.devconyangon.data.cache.columnadapter

import com.squareup.sqldelight.ColumnAdapter
import org.threeten.bp.Instant

/**
 * Created by Vincent on 2019-10-10
 */
object InstantColumnAdapter : ColumnAdapter<Instant, Long> {

  override fun decode(databaseValue: Long): Instant {
    return Instant.ofEpochMilli(databaseValue)
  }

  override fun encode(value: Instant): Long {
    return value.toEpochMilli()
  }

}

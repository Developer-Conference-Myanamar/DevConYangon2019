package org.devconmyanmar.devconyangon.data.cache.columnadapter

import com.squareup.sqldelight.ColumnAdapter
import org.devconmyanmar.devconyangon.domain.model.SpeakerId

/**
 * Created by Vincent on 2019-10-10
 */
object SpeakerIdColumnAdapter : ColumnAdapter<SpeakerId, Long> {

  override fun decode(databaseValue: Long): SpeakerId {
    return SpeakerId(databaseValue)
  }

  override fun encode(value: SpeakerId): Long {
    return value.value
  }

}

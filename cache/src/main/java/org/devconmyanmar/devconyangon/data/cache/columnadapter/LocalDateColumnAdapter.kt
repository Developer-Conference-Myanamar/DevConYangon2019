package org.devconmyanmar.devconyangon.data.cache.columnadapter

import com.squareup.sqldelight.ColumnAdapter
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

/**
 * Created by Vincent on 2019-10-10
 */
object LocalDateColumnAdapter : ColumnAdapter<LocalDate, String> {

  private val dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE

  override fun decode(databaseValue: String): LocalDate {
    return LocalDate.parse(databaseValue, dateTimeFormatter)
  }

  override fun encode(value: LocalDate): String {
    return value.format(dateTimeFormatter)
  }

}

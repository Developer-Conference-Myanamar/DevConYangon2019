package org.devconmyanmar.devconyangon.data.datasource

import org.devconmyanmar.devconyangon.data.entity.SessionEntity
import org.threeten.bp.Instant
import org.threeten.bp.LocalDate

/**
 * Created by Vincent on 11/3/19
 */
interface SessionCacheDataSource {

  fun putSessionEntities(sessionEntities: List<SessionEntity>)

  fun getSessionEntities(date: LocalDate): List<SessionEntity>
}
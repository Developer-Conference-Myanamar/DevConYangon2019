package org.devconmyanmar.devconyangon.data.datasource

import org.devconmyanmar.devconyangon.data.entity.SessionEntity
import org.threeten.bp.LocalDate

/**
 * Created by Vincent on 11/3/19
 */
interface SessionNetworkDataSource {

  fun getSessions(date: LocalDate): List<SessionEntity>

}
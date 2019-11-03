package org.devconmyanmar.devconyangon.domain.repository

import org.devconmyanmar.devconyangon.domain.model.SessionListing
import org.threeten.bp.LocalDate

/**
 * Created by Vincent on 11/3/19
 */
interface SessionRepository {

  suspend fun getSessionListing(date: LocalDate): List<SessionListing>
}

package org.devconmyanmar.devconyangon.domain.repository

import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.model.SessionListing
import org.devconmyanmar.devconyangon.domain.model.Speaker
import org.devconmyanmar.devconyangon.domain.model.SpeakerId
import org.threeten.bp.LocalDate

/**
 * Created by Vincent on 11/3/19
 */
interface SessionRepository {

  suspend fun getSessionListing(date: LocalDate): List<SessionListing>

  suspend fun getFavoriteSessions(date: LocalDate): List<SessionListing>

  suspend fun toggleFavoriteStatus(sessionId: SessionId)

  suspend fun getSessionDetail(sessionId: SessionId):SessionListing

  suspend fun getSpeakerDetail(speakerId: SpeakerId):Speaker
}

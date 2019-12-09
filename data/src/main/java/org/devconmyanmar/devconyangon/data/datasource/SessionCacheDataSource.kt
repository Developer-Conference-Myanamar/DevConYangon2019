package org.devconmyanmar.devconyangon.data.datasource

import org.devconmyanmar.devconyangon.data.entity.SessionEntity
import org.devconmyanmar.devconyangon.data.entity.SpeakerEntity
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.model.Speaker
import org.devconmyanmar.devconyangon.domain.model.SpeakerId
import org.threeten.bp.LocalDate

/**
 * Created by Vincent on 11/3/19
 */
interface SessionCacheDataSource {

  fun putSessionEntities(sessionEntities: List<SessionEntity>)

  fun getSessionEntities(date: LocalDate): List<SessionEntity>

  fun getFavoriteSessionEntities(date: LocalDate): List<SessionEntity>

  fun getFavoriteStatus(sessionId: SessionId): Boolean

  fun updateFavoriteStatus(sessionId: SessionId, favoriteStatus: Boolean)

  fun getSessionDetail(sessionId: SessionId):SessionEntity

  fun getSpeakerDetail(speakerId: SpeakerId): Speaker
}
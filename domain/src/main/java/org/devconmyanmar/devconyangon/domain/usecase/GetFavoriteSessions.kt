package org.devconmyanmar.devconyangon.domain.usecase

import org.devconmyanmar.devconyangon.domain.CoroutineUseCase
import org.devconmyanmar.devconyangon.domain.model.Session
import org.devconmyanmar.devconyangon.domain.repository.SessionRepository
import org.threeten.bp.LocalDate
import javax.inject.Inject

/**
 * Created by Vincent on 11/5/19
 */

class GetFavoriteSessions @Inject constructor(
  private val sessionRepository: SessionRepository
) : CoroutineUseCase<GetFavoriteSessions.Params, List<Session>>() {

  override suspend fun provide(params: Params): List<Session> {
    return sessionRepository.getFavoriteSessions(params.date)
  }

  data class Params(
    val date: LocalDate
  )
}
package org.devconmyanmar.devconyangon.domain.usecase

import org.devconmyanmar.devconyangon.domain.CoroutineUseCase
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.repository.SessionRepository
import javax.inject.Inject

/**
 * Created by Vincent on 2019-11-02
 */
class ToggleSessionFavorite @Inject constructor(
  private val sessionRepository: SessionRepository
) :
  CoroutineUseCase<ToggleSessionFavorite.Params, Unit>() {

  override suspend fun provide(params: Params): Unit {
    return sessionRepository.toggleFavoriteStatus(params.sessionId)
  }

  data class Params(
    val sessionId: SessionId
  )
}
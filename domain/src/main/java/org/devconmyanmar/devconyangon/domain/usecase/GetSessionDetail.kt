package org.devconmyanmar.devconyangon.domain.usecase

import org.devconmyanmar.devconyangon.domain.CoroutineUseCase
import org.devconmyanmar.devconyangon.domain.model.Session
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.repository.SessionRepository
import javax.inject.Inject

/**
 * Created by Vincent on 11/12/19
 */
class GetSessionDetail @Inject constructor(
  private val sessionRepository: SessionRepository
) : CoroutineUseCase<SessionId, Session>() {

  override suspend fun provide(params: SessionId): Session {
    return sessionRepository.getSession(params)
  }

}
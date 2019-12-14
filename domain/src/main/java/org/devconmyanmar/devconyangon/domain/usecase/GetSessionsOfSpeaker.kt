package org.devconmyanmar.devconyangon.domain.usecase

import org.devconmyanmar.devconyangon.domain.CoroutineUseCase
import org.devconmyanmar.devconyangon.domain.model.Session
import org.devconmyanmar.devconyangon.domain.model.SpeakerId
import org.devconmyanmar.devconyangon.domain.repository.SessionRepository
import javax.inject.Inject

/**
 * Created by Vincent on 12/14/19
 */
class GetSessionsOfSpeaker @Inject constructor(
  private val sessionRepository: SessionRepository
) : CoroutineUseCase<SpeakerId, List<Session>>() {

  override suspend fun provide(params: SpeakerId): List<Session> {
    return sessionRepository.getSessionOfSpeaker(params)
  }

}
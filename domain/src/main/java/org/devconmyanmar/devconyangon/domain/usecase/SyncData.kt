package org.devconmyanmar.devconyangon.domain.usecase

import org.devconmyanmar.devconyangon.domain.CoroutineUseCase
import org.devconmyanmar.devconyangon.domain.repository.SessionRepository
import org.devconmyanmar.devconyangon.domain.repository.SpeakerRepository
import javax.inject.Inject

/**
 * Created by Vincent on 12/15/19
 */
class SyncData @Inject constructor(
  private val sessionRepository: SessionRepository,
  private val speakerRepository: SpeakerRepository
) : CoroutineUseCase<Unit, Unit>() {

  override suspend fun provide(params: Unit) {
    sessionRepository.downloadSessions()
    speakerRepository.downloadSpeakers()
  }

}
package org.devconmyanmar.devconyangon.domain.usecase

import org.devconmyanmar.devconyangon.domain.CoroutineUseCase
import org.devconmyanmar.devconyangon.domain.repository.SessionRepository
import org.devconmyanmar.devconyangon.domain.repository.SpeakerRepository
import javax.inject.Inject

/**
 * Created by Vincent on 12/17/19
 */
class RequireToSyncData @Inject constructor(
  private val sessionRepository: SessionRepository,
  private val speakerRepository: SpeakerRepository
) : CoroutineUseCase<Unit, Boolean>() {

  override suspend fun provide(params: Unit): Boolean {
    val sessionList = sessionRepository.getAllSessions()

    val speakerList = speakerRepository.getAllSpeakers()

    return sessionList.isEmpty() || speakerList.isEmpty()
  }

}
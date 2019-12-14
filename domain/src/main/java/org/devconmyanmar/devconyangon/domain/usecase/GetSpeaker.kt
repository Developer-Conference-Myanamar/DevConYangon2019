package org.devconmyanmar.devconyangon.domain.usecase

import org.devconmyanmar.devconyangon.domain.CoroutineUseCase
import org.devconmyanmar.devconyangon.domain.model.Speaker
import org.devconmyanmar.devconyangon.domain.model.SpeakerId
import org.devconmyanmar.devconyangon.domain.repository.SpeakerRepository
import javax.inject.Inject

/**
 * Created by Vincent on 12/14/19
 */
class GetSpeaker @Inject constructor(
  private val speakerRepository: SpeakerRepository
) : CoroutineUseCase<SpeakerId, Speaker>() {

  override suspend fun provide(params: SpeakerId): Speaker {
    return speakerRepository.getSpeaker(params)
  }

}
package org.devconmyanmar.devconyangon.domain.usecase

import org.devconmyanmar.devconyangon.domain.CoroutineUseCase
import org.devconmyanmar.devconyangon.domain.model.Speaker
import org.devconmyanmar.devconyangon.domain.model.SpeakerId
import org.devconmyanmar.devconyangon.domain.repository.SessionRepository
import javax.inject.Inject

class GetSpeaker @Inject constructor(
    private val sessionRepository: SessionRepository
):CoroutineUseCase<GetSpeaker.Params,Speaker>(){
    override suspend fun provide(params: Params): Speaker {
        return sessionRepository.getSpeakerDetail(params.id)
    }

    data class Params(
        val id:SpeakerId
    )
}


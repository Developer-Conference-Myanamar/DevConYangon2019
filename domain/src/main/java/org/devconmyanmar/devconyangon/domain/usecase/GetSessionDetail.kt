package org.devconmyanmar.devconyangon.domain.usecase

import org.devconmyanmar.devconyangon.domain.CoroutineUseCase
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.model.SessionListing
import org.devconmyanmar.devconyangon.domain.repository.SessionRepository
import org.threeten.bp.LocalDate
import javax.inject.Inject

class GetSessionDetail @Inject constructor(
private val sessionRepository: SessionRepository
) :
CoroutineUseCase<GetSessionDetail.Params, SessionListing>() {
    override suspend fun provide(params: Params): SessionListing {
        return sessionRepository.getSessionDetail(
            params.id
        )
    }


    data class Params(
        val id:SessionId
    )
}
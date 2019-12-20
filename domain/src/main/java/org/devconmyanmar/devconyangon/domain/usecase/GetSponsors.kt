package org.devconmyanmar.devconyangon.domain.usecase

import org.devconmyanmar.devconyangon.domain.CoroutineUseCase
import org.devconmyanmar.devconyangon.domain.model.Sponsor
import org.devconmyanmar.devconyangon.domain.model.SponsorId
import org.devconmyanmar.devconyangon.domain.repository.SponsorRepository
import javax.inject.Inject

class GetSponsors @Inject constructor(
    private val sponsorRepository: SponsorRepository
) : CoroutineUseCase<GetSponsors.Params, List<Sponsor>>(){

    override suspend fun provide(params: Params):  List<Sponsor> {
        return sponsorRepository.getSponsorList()
    }

    data class Params(
        val value: Long
    )
}
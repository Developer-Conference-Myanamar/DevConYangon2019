package org.devconmyanmar.devconyangon.domain.usecase

import org.devconmyanmar.devconyangon.domain.CoroutineUseCase
import org.devconmyanmar.devconyangon.domain.model.Sponsor
import org.devconmyanmar.devconyangon.domain.model.SponsorId
import org.devconmyanmar.devconyangon.domain.repository.SponsorRepository
import javax.inject.Inject

class GetSponsors @Inject constructor(
    private val sponsorRepository: SponsorRepository
) : CoroutineUseCase<SponsorId, List<Sponsor>>(){
    override suspend fun provide(params: SponsorId):  List<Sponsor> {
        return sponsorRepository.getSponsorList()
    }
}
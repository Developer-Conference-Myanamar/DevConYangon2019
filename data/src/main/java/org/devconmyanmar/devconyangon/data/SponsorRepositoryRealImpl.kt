package org.devconmyanmar.devconyangon.data

import org.devconmyanmar.devconyangon.data.datasource.NetworkDataSource
import org.devconmyanmar.devconyangon.data.entity.SponsorEntityMapper
import org.devconmyanmar.devconyangon.domain.model.Sponsor
import org.devconmyanmar.devconyangon.domain.repository.SponsorRepository
import javax.inject.Inject

class SponsorRepositoryRealImpl @Inject constructor(
  private val networkDataSource: NetworkDataSource,
  private val sponsorEntityMapper: SponsorEntityMapper
) : SponsorRepository {

  override suspend fun getSponsorList(): List<Sponsor> {
    return networkDataSource.getAllSponsors().map(sponsorEntityMapper::map)
  }

}
package org.devconmyanmar.devconyangon.data

import org.devconmyanmar.devconyangon.data.datasource.SessionNetworkDataSource
import org.devconmyanmar.devconyangon.data.entity.SessionEntityToListingMapper
import org.devconmyanmar.devconyangon.domain.model.SessionListing
import org.devconmyanmar.devconyangon.domain.repository.SessionRepository
import org.threeten.bp.LocalDate
import javax.inject.Inject

/**
 * Created by Vincent on 11/3/19
 */
class SessionRepositoryRealImpl @Inject constructor(
  private val sessionNetworkDataSource: SessionNetworkDataSource,
  private val sessionEntityToListingMapper: SessionEntityToListingMapper
) : SessionRepository {

  override suspend fun getSessionListing(date: LocalDate): List<SessionListing> {
    return sessionNetworkDataSource.getSessions(date).map {
      sessionEntityToListingMapper.map(it)
    }
  }

}
package org.devconmyanmar.devconyangon.data

import org.devconmyanmar.devconyangon.data.datasource.NetworkDataSource
import org.devconmyanmar.devconyangon.data.datasource.SpeakerCacheDataSource
import org.devconmyanmar.devconyangon.data.entity.SessionEntityMapper
import org.devconmyanmar.devconyangon.data.entity.SpeakerEntityMapper
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.model.Speaker
import org.devconmyanmar.devconyangon.domain.model.SpeakerId
import org.devconmyanmar.devconyangon.domain.repository.SpeakerRepository
import javax.inject.Inject

/**
 * Created by Vincent on 12/14/19
 */
class SpeakerRepositoryImpl @Inject constructor(
  private val speakerCacheDataSource: SpeakerCacheDataSource,
  private val networkDataSource: NetworkDataSource,
  private val sessionEntityMapper: SessionEntityMapper,
  private val speakerEntityMapper: SpeakerEntityMapper
) : SpeakerRepository {

  override suspend fun getSpeakersOfSession(sessionId: SessionId): List<Speaker> {
    return speakerCacheDataSource.getSpeakerOfSession(sessionId).map(speakerEntityMapper::map)
  }

  override suspend fun getSpeaker(speakerId: SpeakerId): Speaker {
    val speakerEntity = speakerCacheDataSource.getSpeakerBySpeakerId(speakerId)

    return speakerEntityMapper.map(speakerEntity)
  }

  override suspend fun downloadSpeakers() {
    val speakerEntities = networkDataSource.getAllSpeakers()
    speakerCacheDataSource.putSpeakers(speakerEntities)
  }

}
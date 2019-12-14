package org.devconmyanmar.devconyangon.data.cache

import org.devconmyanmar.devconyangon.DevConYangonDb
import org.devconmyanmar.devconyangon.data.cache.mapper.SessionTableMapper
import org.devconmyanmar.devconyangon.data.cache.mapper.SpeakerTableMapper
import org.devconmyanmar.devconyangon.data.datasource.SpeakerCacheDataSource
import org.devconmyanmar.devconyangon.data.entity.SpeakerEntity
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.model.Speaker
import org.devconmyanmar.devconyangon.domain.model.SpeakerId
import javax.inject.Inject

/**
 * Created by Vincent on 12/14/19
 */
class SpeakerCacheDataSourceImpl @Inject constructor(
  private val db: DevConYangonDb,
  private val speakerTableMapper: SpeakerTableMapper
) : SpeakerCacheDataSource {

  override fun putSpeakers(speakers: List<SpeakerEntity>) {
    db.transaction {
      speakers.forEach { speakerEntity ->
        db.speakerTableQueries.insert_or_replace(
          speakerEntity.speakerId,
          speakerEntity.position,
          speakerEntity.biography,
          speakerEntity.position,
          speakerEntity.imageUrl
        )

        speakerEntity.sessionList.forEach { sessionId ->
          db.sessionSpeakerTableQueries.insert_or_replace(sessionId, speakerEntity.speakerId)
        }
      }
    }
  }

  override fun getSpeakerOfSession(sessionId: SessionId): List<SpeakerEntity> {
    val result = db.speakerTableQueries.select_by_session(sessionId).executeAsList()
    return result.map(speakerTableMapper::map)
  }

  override fun getSpeakerBySpeakerId(speakerId: SpeakerId): SpeakerEntity {
    val result = db.speakerTableQueries.select_by_id(speakerId).executeAsOne()
    return speakerTableMapper.map(result)
  }
}


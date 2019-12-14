package org.devconmyanmar.devconyangon.data.datasource

import org.devconmyanmar.devconyangon.data.entity.SessionEntity
import org.devconmyanmar.devconyangon.data.entity.SpeakerEntity
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.model.Speaker
import org.devconmyanmar.devconyangon.domain.model.SpeakerId
import org.threeten.bp.LocalDate

/**
 * Created by Vincent on 11/3/19
 */
interface SpeakerCacheDataSource {

  fun putSpeakers(speakers: List<SpeakerEntity>)

  fun getSpeakerOfSession(sessionId: SessionId) : List<SpeakerEntity>

  fun getSpeakerBySpeakerId(speakerId: SpeakerId) : SpeakerEntity



}
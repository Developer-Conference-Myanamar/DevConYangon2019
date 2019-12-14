package org.devconmyanmar.devconyangon.domain.repository

import org.devconmyanmar.devconyangon.domain.model.Session
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.model.Speaker
import org.devconmyanmar.devconyangon.domain.model.SpeakerId

/**
 * Created by Vincent on 12/14/19
 */
interface SpeakerRepository {

  suspend fun getSpeakersOfSession(sessionId: SessionId) : List<Speaker>

  suspend fun getSpeaker(speakerId: SpeakerId) : Speaker


}
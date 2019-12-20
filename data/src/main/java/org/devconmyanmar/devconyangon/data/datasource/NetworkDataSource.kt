package org.devconmyanmar.devconyangon.data.datasource

import org.devconmyanmar.devconyangon.data.entity.SessionEntity
import org.devconmyanmar.devconyangon.data.entity.SpeakerEntity
import org.devconmyanmar.devconyangon.data.entity.SponsorEntity

/**
 * Created by Vincent on 12/14/19
 */
interface NetworkDataSource {

  fun getAllSpeakers(): List<SpeakerEntity>

  fun getAllSession(): List<SessionEntity>

  fun getAllSponsors(): List<SponsorEntity>
}
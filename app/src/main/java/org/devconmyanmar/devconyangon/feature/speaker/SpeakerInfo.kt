package org.devconmyanmar.devconyangon.feature.speaker

import org.devconmyanmar.devconyangon.domain.mapper.UnidirectionalMap
import org.devconmyanmar.devconyangon.domain.model.Speaker
import org.devconmyanmar.devconyangon.domain.model.SpeakerId
import javax.inject.Inject

data class SpeakerInfo(
    val speakerId: SpeakerId,
    val name: String
)

class SpeakerInfoMapper @Inject constructor():UnidirectionalMap<Speaker,SpeakerInfo>{
    override fun map(item: Speaker): SpeakerInfo {
        return SpeakerInfo(
            speakerId = item.speakerId,
            name = item.name
        )
    }
}


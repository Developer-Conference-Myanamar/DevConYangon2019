package org.devconmyanmar.devconyangon.data.cache.mapper

import org.devconmyanmar.devconyangon.DevConYangonDb
import org.devconmyanmar.devconyangon.data.cache.SpeakerTable
import org.devconmyanmar.devconyangon.domain.mapper.BidirectionalMap
import org.devconmyanmar.devconyangon.domain.mapper.UnidirectionalMap
import org.devconmyanmar.devconyangon.domain.model.Speaker
import javax.inject.Inject

class SpeakerTableMapper @Inject constructor(
    private val db: DevConYangonDb
) :BidirectionalMap<SpeakerTable, Speaker>{
    override fun map(item: SpeakerTable): Speaker {
        return Speaker(
            speakerId = item.speaker_id,
            name = item.speaker_title
        )
    }

    override fun reverseMap(item: Speaker): SpeakerTable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
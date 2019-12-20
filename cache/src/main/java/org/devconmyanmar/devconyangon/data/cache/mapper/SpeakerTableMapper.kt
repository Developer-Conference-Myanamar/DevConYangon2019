package org.devconmyanmar.devconyangon.data.cache.mapper

import org.devconmyanmar.devconyangon.DevConYangonDb
import org.devconmyanmar.devconyangon.data.cache.SessionTable
import org.devconmyanmar.devconyangon.data.cache.SpeakerTable
import org.devconmyanmar.devconyangon.data.entity.RoomEntity
import org.devconmyanmar.devconyangon.data.entity.SessionEntity
import org.devconmyanmar.devconyangon.data.entity.SpeakerEntity
import org.devconmyanmar.devconyangon.domain.mapper.UnidirectionalMap
import org.threeten.bp.Clock
import javax.inject.Inject

/**
 * Created by Vincent on 11/3/19
 */
class SpeakerTableMapper @Inject constructor(
  private val db: DevConYangonDb
) : UnidirectionalMap<SpeakerTable, SpeakerEntity> {

  override fun map(item: SpeakerTable): SpeakerEntity {

//    val sesssio

    return SpeakerEntity(
      speakerId = item.speaker_id,
      name = item.name,
      biography = item.biography,
      position = item.position,
      imageUrl = item.imageUrl,
      sessionList = listOf())

  }
}


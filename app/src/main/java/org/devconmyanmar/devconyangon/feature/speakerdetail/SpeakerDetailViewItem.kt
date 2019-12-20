package org.devconmyanmar.devconyangon.feature.speakerdetail

import org.devconmyanmar.devconyangon.domain.mapper.UnidirectionalMap
import org.devconmyanmar.devconyangon.domain.model.Session
import org.devconmyanmar.devconyangon.domain.model.Speaker
import org.devconmyanmar.devconyangon.domain.model.SpeakerId
import org.devconmyanmar.devconyangon.feature.schedule.session.SessionViewItem
import org.devconmyanmar.devconyangon.feature.schedule.session.SessionViewItemListMapper
import javax.inject.Inject

/**
 * Created by Vincent on 12/15/19
 */
data class SpeakerDetailViewItem(
  val speakerId: SpeakerId,
  val name: String,
  val biography: String,
  val position: String,
  val imageUrl: String,
  val sessionList: List<SessionViewItem>

)

class SpeakerDetailViewItemMapper @Inject constructor(
  private val sessionViewItemListMapper: SessionViewItemListMapper
) : UnidirectionalMap<SpeakerDetailViewItemMapper.Params, SpeakerDetailViewItem> {

  data class Params(
    val speaker: Speaker,
    val sessionList: List<Session>
  )

  override fun map(item: Params): SpeakerDetailViewItem {
    val speaker = item.speaker
    return SpeakerDetailViewItem(
      speakerId = speaker.speakerId,
      name = speaker.name,
      biography = speaker.biography,
      position = if (speaker.position.isEmpty()) {
        "-"
      } else {
        speaker.position
      },
      imageUrl = speaker.imageUrl,
      sessionList = sessionViewItemListMapper.map(item.sessionList)
    )
  }

}
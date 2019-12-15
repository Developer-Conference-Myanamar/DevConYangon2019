package org.devconmyanmar.devconyangon.feature.speakerdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.devconmyanmar.devconyangon.base.core.mvp.BaseViewModel
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.usecase.GetSessionsOfSpeaker
import org.devconmyanmar.devconyangon.domain.usecase.GetSpeaker
import org.devconmyanmar.devconyangon.domain.usecase.ToggleSessionFavorite
import org.devconmyanmar.devconyangon.feature.sessiondetail.SessionDetailViewItem
import javax.inject.Inject

/**
 * Created by Vincent on 12/14/19
 */
class SpeakerDetailViewModel @Inject constructor(
  private val speakerDetailViewItemMapper : SpeakerDetailViewItemMapper,
  private val getSpeaker: GetSpeaker,
  private val getSessionsOfSpeaker: GetSessionsOfSpeaker,
  private val toggleSessionFavorite: ToggleSessionFavorite
) : BaseViewModel<SpeakerDetailView>() {

  private val speakerDetailViewItemLiveData = MutableLiveData<SpeakerDetailViewItem>()

  override fun attachView(viewable: SpeakerDetailView) {
    super.attachView(viewable)
    view?.subscribeToSessionDetailViewItemLiveData(speakerDetailViewItemLiveData)
  }

  fun loadSpeakerDetail() {
    viewModelScope.launch {
      val speakerId = view?.getSpeakerId()

      if (speakerId != null) {
        val speaker = getSpeaker.execute(speakerId)
        val sessionList = getSessionsOfSpeaker.execute(speakerId)

        val mapperParams = SpeakerDetailViewItemMapper.Params(speaker, sessionList)

        val speakerDetailViewItem = speakerDetailViewItemMapper.map(mapperParams)
        speakerDetailViewItemLiveData.postValue(speakerDetailViewItem)
      }
    }
  }

  fun toggleFavorite(sessionId: SessionId) {
    viewModelScope.launch {
      val params = ToggleSessionFavorite.Params(sessionId)
      toggleSessionFavorite.execute(params)

      loadSpeakerDetail()
    }
  }


}
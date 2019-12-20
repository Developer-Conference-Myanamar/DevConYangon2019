package org.devconmyanmar.devconyangon.feature.speakerdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.devconmyanmar.devconyangon.base.core.mvp.Viewable
import org.devconmyanmar.devconyangon.domain.model.SpeakerId
import org.devconmyanmar.devconyangon.feature.sessiondetail.SessionDetailViewItem

/**
 * Created by Vincent on 12/14/19
 */
interface SpeakerDetailView : Viewable  {

  abstract fun getSpeakerId(): SpeakerId

  abstract fun subscribeToSessionDetailViewItemLiveData(speakerDetailViewItemLiveData: LiveData<SpeakerDetailViewItem>)

}
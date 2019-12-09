package org.devconmyanmar.devconyangon.feature.speaker

import androidx.lifecycle.MutableLiveData
import org.devconmyanmar.devconyangon.base.core.mvp.BaseViewModel
import org.devconmyanmar.devconyangon.domain.model.Speaker

class SpeakerViewModel:BaseViewModel<SpeakerInfoView>() {
    private val speakerInfoListLD:MutableLiveData<List<Speaker>> = MutableLiveData()
    override fun attachView(viewable: SpeakerInfoView) {
        super.attachView(viewable)
        view?.subscribeSpeakerInfo(speakerInfoListLD)
    }

    fun loadSpeaker(speakerID:Int){

    }
}
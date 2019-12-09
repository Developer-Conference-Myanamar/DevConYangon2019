package org.devconmyanmar.devconyangon.feature.speaker

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import org.devconmyanmar.devconyangon.base.core.mvp.BaseViewModel
import org.devconmyanmar.devconyangon.domain.model.Speaker
import org.devconmyanmar.devconyangon.domain.model.SpeakerId
import org.devconmyanmar.devconyangon.domain.usecase.GetSpeaker
import javax.inject.Inject

class SpeakerViewModel @Inject constructor(
    private val getSpeaker: GetSpeaker,
    private val speakerInfoMapper: SpeakerInfoMapper
):BaseViewModel<SpeakerInfoView>() {
    private val speakerInfoListLD:MutableLiveData<SpeakerInfo> = MutableLiveData()
    override fun attachView(viewable: SpeakerInfoView) {
        super.attachView(viewable)
        view?.subscribeSpeakerInfo(speakerInfoListLD)
    }

    fun loadSpeaker(speakerID:Long){
        scope.launch {
            val speaker= getSpeaker.execute(GetSpeaker.Params(SpeakerId(speakerID)))
            val speakerInfo= speakerInfoMapper.map(speaker)
            speakerInfoListLD.postValue(speakerInfo)
        }
    }
}
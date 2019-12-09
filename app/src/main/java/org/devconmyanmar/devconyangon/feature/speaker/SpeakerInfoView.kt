package org.devconmyanmar.devconyangon.feature.speaker

import androidx.lifecycle.LiveData
import org.devconmyanmar.devconyangon.base.core.mvp.Viewable
import org.devconmyanmar.devconyangon.domain.model.Speaker

interface SpeakerInfoView :Viewable{
    fun subscribeSpeakerInfo(speakerInfoListLD: LiveData<List<Speaker>>)
}
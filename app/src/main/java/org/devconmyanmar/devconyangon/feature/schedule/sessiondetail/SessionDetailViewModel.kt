package org.devconmyanmar.devconyangon.feature.schedule.sessiondetail

import androidx.lifecycle.MutableLiveData
import org.devconmyanmar.devconyangon.base.core.mvp.BaseViewModel
import org.devconmyanmar.devconyangon.feature.schedule.ScheduleDateViewItem

class SessionDetailViewModel:BaseViewModel<SessionDetailView>() {
    private val scheduleViewItemLD = MutableLiveData<ScheduleDateViewItem>()

    override fun attachView(viewable: SessionDetailView) {
        super.attachView(viewable)
        view?.subscribeToSessionDateViewItem(scheduleViewItemLD)
    }


}
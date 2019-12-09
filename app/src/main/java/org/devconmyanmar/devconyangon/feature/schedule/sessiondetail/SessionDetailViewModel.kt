package org.devconmyanmar.devconyangon.feature.schedule.sessiondetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.work.Data
import kotlinx.coroutines.launch
import org.devconmyanmar.devconyangon.base.core.mvp.BaseViewModel
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.usecase.GetSessionDetail
import java.util.*
import javax.inject.Inject



class SessionDetailViewModel @Inject constructor(
    private val getSessionDetail: GetSessionDetail,
    private val sessionViewItemMapper: SessionViewItemMapper
):BaseViewModel<SessionDetailView>() {
    private val sessionDetailItemLD = MutableLiveData<SessionDetailItem>()

    override fun attachView(viewable: SessionDetailView) {
        super.attachView(viewable)
        view?.subscribeToSessionDateViewItem(sessionDetailItemLD)
    }

    fun loadSessionDetail(sessionId: Long){
        scope.launch {
            val sessionDetailListing = getSessionDetail.execute(GetSessionDetail.Params(
                SessionId(sessionId)
            ))
            val sessionDetailItem=sessionViewItemMapper.map(sessionDetailListing)
            sessionDetailItemLD.postValue(sessionDetailItem)
        }
    }


}
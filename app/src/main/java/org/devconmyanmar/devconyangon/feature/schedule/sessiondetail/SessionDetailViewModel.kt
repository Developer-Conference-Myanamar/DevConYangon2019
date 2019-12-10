package org.devconmyanmar.devconyangon.feature.schedule.sessiondetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.work.Data
import kotlinx.coroutines.launch
import org.devconmyanmar.devconyangon.base.core.mvp.BaseViewModel
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.usecase.GetSessionDetail
import org.devconmyanmar.devconyangon.domain.usecase.ToggleSessionFavorite
import java.util.*
import javax.inject.Inject



class SessionDetailViewModel @Inject constructor(
    private val toggleSessionFavorite: ToggleSessionFavorite,
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

    fun toggleFavoriteStatus(sessionId: Long) {
        scope.launch {
            toggleSessionFavorite.execute(ToggleSessionFavorite.Params(SessionId(sessionId)))
            loadSessionDetail(sessionId)
        }
    }
}
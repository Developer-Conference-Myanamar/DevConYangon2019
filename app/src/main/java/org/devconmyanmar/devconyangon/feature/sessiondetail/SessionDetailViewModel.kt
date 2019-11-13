package org.devconmyanmar.devconyangon.feature.sessiondetail

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import org.devconmyanmar.devconyangon.base.core.mvp.BaseViewModel
import org.devconmyanmar.devconyangon.domain.usecase.GetSessionDetail
import org.devconmyanmar.devconyangon.domain.usecase.ToggleSessionFavorite
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Vincent on 11/11/19
 */
class SessionDetailViewModel @Inject constructor(
  private val toggleSessionFavorite: ToggleSessionFavorite,
  private val getSessionDetail: GetSessionDetail,
  private val sessionDetailViewItemMapper: SessionDetailViewItemMapper
) : BaseViewModel<SessionDetailView>() {

  private val viewItemLiveData = MutableLiveData<SessionDetailViewItem>()

  override fun attachView(viewable: SessionDetailView) {
    super.attachView(viewable)
    view?.subscribeToViewItemLiveData(viewItemLiveData)
  }

  fun loadSessionDetail() {
    scope.launch {
       try {
         val session = getSessionDetail.execute(
           view?.getSessionId()
             ?: throw IllegalStateException("View has not been attached to viewModel yet")
         )
         viewItemLiveData.postValue(sessionDetailViewItemMapper.map(session))
       } catch (exception: Exception) {
         Timber.e(exception)
       }


    }
  }

  fun toggleFavorite() {
    scope.launch {
      val params = ToggleSessionFavorite.Params(
        view?.getSessionId()
          ?: throw IllegalStateException("View has not been attached to viewModel yet")
      )
      toggleSessionFavorite.execute(params)

      loadSessionDetail()
    }

  }

}
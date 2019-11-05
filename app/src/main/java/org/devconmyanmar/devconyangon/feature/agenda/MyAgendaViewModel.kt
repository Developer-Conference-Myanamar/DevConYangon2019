package org.devconmyanmar.devconyangon.feature.agenda

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import org.devconmyanmar.devconyangon.base.core.mvp.BaseViewModel
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.usecase.GetConferenceDates
import org.devconmyanmar.devconyangon.domain.usecase.GetFavoriteSessions
import org.devconmyanmar.devconyangon.domain.usecase.ToggleSessionFavorite
import javax.inject.Inject

/**
 * Created by Vincent on 2019-11-02
 */
class MyAgendaViewModel @Inject constructor(
  private val getConferenceDates: GetConferenceDates,
  private val getFavoriteSessions: GetFavoriteSessions,
  private val toggleSessionFavorite: ToggleSessionFavorite,
  private val myAgendaViewItemMapper: MyAgendaViewItemMapper
) : BaseViewModel<MyAgendaView>() {

  private val viewItemListLiveData = MutableLiveData<List<MyAgendaViewItem>>()

  override fun attachView(viewable: MyAgendaView) {
    super.attachView(viewable)
    view?.subscribeToViewItemListLiveData(viewItemListLiveData)
  }

  fun loadSessions() {

    scope.launch {

      val dates = getConferenceDates.execute(Unit)

      val viewItemList = dates.map {
        val sessionList = getFavoriteSessions.execute(GetFavoriteSessions.Params(it))
        val params = MyAgendaViewItemMapper.Params(it, sessionList)

        myAgendaViewItemMapper.map(params)
      }

      viewItemListLiveData.postValue(viewItemList)
    }
  }

  fun toggleFavoriteStatus(sessionId: SessionId) {
    scope.launch {
      toggleSessionFavorite.execute(ToggleSessionFavorite.Params(sessionId))
      loadSessions()
    }
  }

}


package org.devconmyanmar.devconyangon.feature.schedule.session

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import org.devconmyanmar.devconyangon.base.core.mvp.BaseViewModel
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.usecase.GetSessions
import org.devconmyanmar.devconyangon.domain.usecase.ToggleSessionFavorite
import org.threeten.bp.LocalDate
import javax.inject.Inject

/**
 * Created by Vincent on 2019-11-02
 */
class SessionViewModel @Inject constructor(
  private val getSessions: GetSessions,
  private val toggleSessionFavorite: ToggleSessionFavorite,
  private val sessionViewItemListMapper: SessionViewItemListMapper
) : BaseViewModel<SessionView>() {

  private val sessionListLiveData = MutableLiveData<List<SessionViewItem>>()
  private var date = LocalDate.now()

  override fun attachView(viewable: SessionView) {
    super.attachView(viewable)
    view?.subscribeToSessionList(sessionListLiveData)
  }

  fun setDate(date: LocalDate) {
    this.date = date
  }

  fun loadSessions() {
    scope.launch {
      val sessionList = getSessions.execute(GetSessions.Params(date))

      val sessionViewItemList = sessionViewItemListMapper.map(sessionList)

      sessionListLiveData.postValue(sessionViewItemList)
    }
  }

  fun toggleFavoriteStatus(sessionId: SessionId) {
    scope.launch {
      toggleSessionFavorite.execute(ToggleSessionFavorite.Params(sessionId))
      loadSessions()
    }
  }
}

package org.devconmyanmar.devconyangon.feature.agenda.session

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import org.devconmyanmar.devconyangon.base.core.mvp.BaseViewModel
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.usecase.GetFavoriteSessions
import org.devconmyanmar.devconyangon.domain.usecase.ToggleSessionFavorite
import org.devconmyanmar.devconyangon.feature.agenda.FirstTimeListPositionFinder
import org.threeten.bp.LocalDate
import javax.inject.Inject

/**
 * Created by Vincent on 11/6/19
 */
class MyAgendaSessionViewModel @Inject constructor(
  private val getFavoriteSessions: GetFavoriteSessions,
  private val toggleSessionFavorite: ToggleSessionFavorite,
  private val myAgendaSessionViewItemMapper: MyAgendaSessionViewItemMapper,
  private val firstTimeListPositionFinder: FirstTimeListPositionFinder
) : BaseViewModel<MyAgendaSessionView>() {

  private val myAgendaSessionViewItemListLiveData = MutableLiveData<List<MyAgendaSessionViewItem>>()

  private var date = LocalDate.now()

  private var hasInitiallyScrolled = false

  override fun attachView(viewable: MyAgendaSessionView) {
    super.attachView(viewable)
    view?.subscribeToMyAgendaSessionViewItemListLiveData(myAgendaSessionViewItemListLiveData)
  }

  fun setDate(date: LocalDate) {
    this.date = date
  }

  fun loadSessions() {

    scope.launch {

      val params = GetFavoriteSessions.Params(date)
      val sessionList = getFavoriteSessions.execute(params)

      val viewItemList = myAgendaSessionViewItemMapper.map(sessionList)
      myAgendaSessionViewItemListLiveData.postValue(viewItemList)

      if (!hasInitiallyScrolled) {
        getIndexToScrollTo()
      }

    }
  }

  fun getIndexToScrollTo() {
    scope.launch {
      val lastValue = myAgendaSessionViewItemListLiveData.value
      if (lastValue != null && date == LocalDate.now()) {
        val indexToScrollTo = firstTimeListPositionFinder.findTimeIndexToScrollTo(lastValue)
        view?.scrollToIndex(indexToScrollTo)
        hasInitiallyScrolled = true

      }
    }
  }

  fun toggleFavoriteStatus(sessionId: SessionId) {
    scope.launch {
      toggleSessionFavorite.execute(ToggleSessionFavorite.Params(sessionId))
      loadSessions()
    }
  }

}
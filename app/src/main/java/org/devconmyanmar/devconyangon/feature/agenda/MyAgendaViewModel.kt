package org.devconmyanmar.devconyangon.feature.agenda

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import org.devconmyanmar.devconyangon.base.core.mvp.BaseViewModel
import org.devconmyanmar.devconyangon.domain.usecase.GetConferenceDates
import javax.inject.Inject

/**
 * Created by Vincent on 2019-11-02
 */
class MyAgendaViewModel @Inject constructor(
  private val getConferenceDates: GetConferenceDates,
  private val myAgendaViewItemMapper: MyAgendaViewItemMapper,
  private val firstTimeListPositionFinder: FirstTimeListPositionFinder
) : BaseViewModel<MyAgendaView>() {

  private val viewItemListLiveData = MutableLiveData<List<MyAgendaDateViewItem>>()

  private var hasInitiallyScrolled = false

  override fun attachView(viewable: MyAgendaView) {
    super.attachView(viewable)
    view?.subscribeToViewItemListLiveData(viewItemListLiveData)
  }

  fun loadSessions() {

    scope.launch {

      val dates = getConferenceDates.execute(Unit)
      //Find which index to scroll first to

      viewItemListLiveData.postValue(dates.map(myAgendaViewItemMapper::map))

      if (!hasInitiallyScrolled) {
        getIndexToScrollTo()
      }
    }
  }

  fun getIndexToScrollTo() {
    scope.launch {
      val lastValue = viewItemListLiveData.value
      if (lastValue != null) {
        val indexToScrollTo = firstTimeListPositionFinder.findDateIndexToScrollTo(lastValue)
        view?.scrollToIndex(indexToScrollTo)
        hasInitiallyScrolled = true

      }
    }
  }

}


package org.devconmyanmar.devconyangon.feature.schedule

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import org.devconmyanmar.devconyangon.base.core.mvp.BaseViewModel
import org.devconmyanmar.devconyangon.domain.usecase.GetConferenceDates
import javax.inject.Inject

/**
 * Created by Vincent on 2019-11-01
 */
class ScheduleViewModel @Inject constructor(
  private val getConferenceDates: GetConferenceDates
) : BaseViewModel<ScheduleView>() {

  private val scheduleDateViewItemLiveData = MutableLiveData<List<ScheduleDateViewItem>>()

  override fun attachView(viewable: ScheduleView) {
    super.attachView(viewable)
    view?.subscribeToScheduleDateViewItemLiveData(scheduleDateViewItemLiveData)
  }

  fun loadConferenceDates() {
    scope.launch {
      val scheduleDateViewItems = getConferenceDates.execute(Unit).map(ScheduleDateViewItem.Mapper::map)
      scheduleDateViewItemLiveData.postValue(scheduleDateViewItems)
    }
  }

}
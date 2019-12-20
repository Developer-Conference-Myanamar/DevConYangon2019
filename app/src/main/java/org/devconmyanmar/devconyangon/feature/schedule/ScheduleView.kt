package org.devconmyanmar.devconyangon.feature.schedule

import androidx.lifecycle.LiveData
import org.devconmyanmar.devconyangon.base.core.mvp.Viewable

/**
 * Created by Vincent on 2019-11-01
 */
interface ScheduleView : Viewable {
  fun subscribeToScheduleDateViewItemLiveData(scheduleDateViewItemLiveData: LiveData<List<ScheduleDateViewItem>>)

}
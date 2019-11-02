package org.devconmyanmar.devconyangon.feature.schedule

import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.base.core.mvp.MvpFragment

/**
 * Created by Vincent on 2019-11-01
 */
class ScheduleFragment : MvpFragment<ScheduleView, ScheduleViewModel>(), ScheduleView {

  override val viewModel: ScheduleViewModel by contractedViewModel()

  override val layoutId: Int
    get() = R.layout.fragment_schedule

}
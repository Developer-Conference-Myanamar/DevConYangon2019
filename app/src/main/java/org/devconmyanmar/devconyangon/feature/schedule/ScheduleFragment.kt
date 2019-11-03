package org.devconmyanmar.devconyangon.feature.schedule

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayoutMediator
import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.base.core.mvp.MvpFragment
import org.devconmyanmar.devconyangon.databinding.FragmentScheduleBinding

/**
 * Created by Vincent on 2019-11-01
 */
class ScheduleFragment : MvpFragment<ScheduleView, ScheduleViewModel>(), ScheduleView {

  override val viewModel: ScheduleViewModel by contractedViewModel()

  override val layoutId: Int
    get() = R.layout.fragment_schedule

  private val binding by lazy {
    FragmentScheduleBinding.bind(view!!)
  }

  private val scheduleViewPagerAdapter by lazy {
    ScheduleViewPagerAdapter(this)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.viewPager.adapter = scheduleViewPagerAdapter

    TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
      val itemAtIndex = scheduleViewPagerAdapter.getItemAtPosition(position)
      tab.text = itemAtIndex.dateAsString
    }.attach()
    viewModel.loadConferenceDates()
  }

  override fun subscribeToScheduleDateViewItemLiveData(scheduleDateViewItemLiveData: LiveData<List<ScheduleDateViewItem>>) {
    scheduleDateViewItemLiveData.observe(viewLifecycleOwner, Observer {
      scheduleViewPagerAdapter.setItems(it)
    })
  }

}
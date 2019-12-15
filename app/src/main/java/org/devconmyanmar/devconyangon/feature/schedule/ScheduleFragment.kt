package org.devconmyanmar.devconyangon.feature.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayoutMediator
import org.devconmyanmar.devconyangon.base.core.mvp.MvpFragment
import org.devconmyanmar.devconyangon.databinding.FragmentScheduleBinding
import org.devconmyanmar.devconyangon.feature.schedule.filter.FilterModalBottomSheet
import timber.log.Timber

/**
 * Created by Vincent on 2019-11-01
 */
class ScheduleFragment : MvpFragment<FragmentScheduleBinding, ScheduleView, ScheduleViewModel>(),
  ScheduleView {

  override val viewModel: ScheduleViewModel by contractedViewModel()

  override fun bindView(inflater: LayoutInflater): FragmentScheduleBinding {
    return FragmentScheduleBinding.inflate(inflater)
  }

  private val scheduleViewPagerAdapter by lazy {
    ScheduleViewPagerAdapter(this)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

//    try {
//      binding.viewPager.adapter = scheduleViewPagerAdapter
//    } catch(exception : Exception) {
//      Timber.e(exception)
//    }



    viewModel.loadConferenceDates()

    binding.fabFilter.setOnClickListener {
      showBottomSheetDialog()
    }
  }

  override fun subscribeToScheduleDateViewItemLiveData(scheduleDateViewItemLiveData: LiveData<List<ScheduleDateViewItem>>) {
    scheduleDateViewItemLiveData.observe(viewLifecycleOwner, Observer {
      scheduleViewPagerAdapter.setItems(it)
    })
  }

  private fun showBottomSheetDialog() {
    val fragment = FilterModalBottomSheet()
    fragment.show(childFragmentManager, "TAG")
  }

  override fun onPause() {
    binding.viewPager.adapter = null
    super.onPause()
  }

  override fun onResume() {
    super.onResume()
    binding.viewPager.adapter = scheduleViewPagerAdapter
    TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
      val itemAtIndex = scheduleViewPagerAdapter.getItemAtPosition(position)
      tab.text = itemAtIndex.dateAsString
    }.attach()
  }

}
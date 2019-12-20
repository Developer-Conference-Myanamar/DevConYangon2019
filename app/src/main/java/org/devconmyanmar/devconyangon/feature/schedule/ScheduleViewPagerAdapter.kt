package org.devconmyanmar.devconyangon.feature.schedule

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.devconmyanmar.devconyangon.feature.schedule.session.SessionFragment

/**
 * Created by Vincent on 2019-11-02
 */
class ScheduleViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

  private var listItems = listOf<ScheduleDateViewItem>()

  fun setItems(listItems: List<ScheduleDateViewItem>) {
    this.listItems = listItems
    notifyDataSetChanged()
  }

  fun getItemAtPosition(position: Int): ScheduleDateViewItem {
    return listItems[position]
  }

  fun getItemAtPositionOrNull(position: Int): ScheduleDateViewItem? {
    return listItems.getOrNull(position)
  }

  override fun getItemCount(): Int {
    return this.listItems.size
  }

  override fun createFragment(position: Int): Fragment {
    return SessionFragment.newInstance(listItems[position].date)
  }

}
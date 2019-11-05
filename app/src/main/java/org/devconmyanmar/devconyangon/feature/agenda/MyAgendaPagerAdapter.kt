package org.devconmyanmar.devconyangon.feature.agenda

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.devconmyanmar.devconyangon.feature.agenda.session.MyAgendaSessionFragment

/**
 * Created by Vincent on 11/4/19
 */
class MyAgendaPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

  private var listItems = listOf<MyAgendaViewItem>()

  fun setItems(listItems: List<MyAgendaViewItem>) {
    this.listItems = listItems
    notifyDataSetChanged()
  }

  fun getItemAtPosition(position: Int): MyAgendaViewItem {
    return listItems[position]
  }

  override fun getItemCount(): Int {
    return listItems.size
  }

  override fun createFragment(position: Int): Fragment {
    return MyAgendaSessionFragment(listItems[position].date)
  }

}
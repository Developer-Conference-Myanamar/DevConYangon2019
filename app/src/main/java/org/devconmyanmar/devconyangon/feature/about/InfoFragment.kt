package org.devconmyanmar.devconyangon.feature.about

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.base.core.mvp.MvpFragment

/**
 * Created by Vincent on 2019-11-02
 */
class InfoFragment : MvpFragment<InfoView, InfoViewModel>(), InfoView {

  override val viewModel: InfoViewModel by contractedViewModel()

  override val layoutId: Int
    get() = R.layout.fragment_info


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val infoViewPager=view.findViewById<ViewPager2>(R.id.infoViewPager)
    val infoTabs=view.findViewById<TabLayout>(R.id.infoTabs)
    infoViewPager.adapter=InfoViewPagerAdapter()
    TabLayoutMediator(infoTabs, infoViewPager,
      TabLayoutMediator.TabConfigurationStrategy { tab, position ->
        when(position){
          0->tab.text="Setting"
          1->tab.text="About"
          2->tab.text="Sponsor"
        }
      }).attach()
  }

}
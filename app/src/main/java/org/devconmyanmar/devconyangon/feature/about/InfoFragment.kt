package org.devconmyanmar.devconyangon.feature.about

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.base.core.mvp.MvpFragment
import org.devconmyanmar.devconyangon.databinding.FragmentInfoBinding

/**
 * Created by Vincent on 2019-11-02
 */
class InfoFragment : MvpFragment<InfoView, InfoViewModel>(), InfoView {

  override val viewModel: InfoViewModel by contractedViewModel()

  override val layoutId: Int
    get() = R.layout.fragment_info

  private val binding by lazy{
    FragmentInfoBinding.bind(view!!)
  }

  private val infoViewPagerAdapter by lazy {
    MoreViewPagerAdapter(this)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.infoViewPager.adapter = infoViewPagerAdapter
    TabLayoutMediator(binding.infoTabs, binding.infoViewPager) { tab, position ->
      tab.text =  infoViewPagerAdapter.getItemAtPosition(position)
    }.attach()
  }

}
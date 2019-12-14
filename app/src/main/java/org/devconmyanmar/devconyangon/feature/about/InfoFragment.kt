package org.devconmyanmar.devconyangon.feature.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import org.devconmyanmar.devconyangon.base.core.mvp.MvpFragment
import org.devconmyanmar.devconyangon.databinding.FragmentInfoBinding

/**
 * Created by Vincent on 2019-11-02
 */
class InfoFragment : MvpFragment<FragmentInfoBinding, InfoView, InfoViewModel>(), InfoView {

  override val viewModel: InfoViewModel by contractedViewModel()

  override fun bindView(inflater: LayoutInflater): FragmentInfoBinding =
    FragmentInfoBinding.inflate(inflater)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.viewPager.adapter = InfoViewPagerAdapter()
    TabLayoutMediator(binding.tabLayout, binding.viewPager,
      TabLayoutMediator.TabConfigurationStrategy { tab, position ->
        when (position) {
          0 -> tab.text = "Setting"
          1 -> tab.text = "About"
          2 -> tab.text = "Sponsor"
        }
      }).attach()
  }

}
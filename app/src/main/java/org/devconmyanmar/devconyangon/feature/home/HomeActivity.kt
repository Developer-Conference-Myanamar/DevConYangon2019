package org.devconmyanmar.devconyangon.feature.home

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_home.bottomNavigationView
import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.base.core.mvp.MvpActivity

/**
 * Created by Vincent on 2019-11-01
 */
class HomeActivity : MvpActivity<HomeView, HomeViewModel>(), HomeView {

  override val viewModel: HomeViewModel by contractedViewModels()

  override val layoutResId: Int
    get() = R.layout.activity_home

  override fun onPostCreate(savedInstanceState: Bundle?) {
    super.onPostCreate(savedInstanceState)
    val navController = findNavController(R.id.navFragment)
    bottomNavigationView.setupWithNavController(navController)
  }

}
package org.devconmyanmar.devconyangon.feature.home

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.base.core.mvp.MvpActivity
import org.devconmyanmar.devconyangon.databinding.ActivityHomeBinding

/**
 * Created by Vincent on 2019-11-01
 */
class HomeActivity : MvpActivity<ActivityHomeBinding, HomeView, HomeViewModel>(), HomeView {

  override val viewModel: HomeViewModel by contractedViewModel()

//  override val layoutResId: Int
//    get() = R.layout.activity_home

//  private val bottomNavigationView by lazy {
//    findViewById<BottomNavigationView>(R.id.bottomNavigationView)
//  }

  override val binding: ActivityHomeBinding by lazy {
    ActivityHomeBinding.inflate(layoutInflater)
  }

  private val navController by lazy {
    findNavController(R.id.navFragment)
  }

  override fun onPostCreate(savedInstanceState: Bundle?) {
    super.onPostCreate(savedInstanceState)

    binding.bottomNavigationView.setupWithNavController(navController)
  }

  override fun onBackPressed() {
    if (!navController.popBackStack()) {
      super.onBackPressed()
    }
  }

}
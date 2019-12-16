package org.devconmyanmar.devconyangon.feature.home

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.base.core.mvp.MvpActivity
import org.devconmyanmar.devconyangon.databinding.ActivityHomeBinding
import org.devconmyanmar.devconyangon.feature.sync.SyncWorker
import org.threeten.bp.Duration
import java.util.concurrent.TimeUnit

/**
 * Created by Vincent on 2019-11-01
 */
class HomeActivity : MvpActivity<ActivityHomeBinding, HomeView, HomeViewModel>(), HomeView {

  override val viewModel: HomeViewModel by contractedViewModel()

  override val binding: ActivityHomeBinding by lazy {
    ActivityHomeBinding.inflate(layoutInflater)
  }

  private val navController by lazy {
    findNavController(R.id.navFragment)
  }

  override fun onPostCreate(savedInstanceState: Bundle?) {
    super.onPostCreate(savedInstanceState)

    val request =
      PeriodicWorkRequestBuilder<SyncWorker>(Duration.ofHours(1).seconds, TimeUnit.SECONDS)
        .build()

    WorkManager.getInstance(this).enqueueUniquePeriodicWork(
      SyncWorker.NAME,
      ExistingPeriodicWorkPolicy.REPLACE,
      request
    )

    binding.bottomNavigationView.setupWithNavController(navController)
  }

  override fun onBackPressed() {
    if (!navController.popBackStack()) {
      super.onBackPressed()
    }
  }

}
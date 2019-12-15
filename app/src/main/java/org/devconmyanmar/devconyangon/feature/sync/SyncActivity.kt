package org.devconmyanmar.devconyangon.feature.sync

import android.content.Intent
import android.os.Bundle
import org.devconmyanmar.devconyangon.base.core.mvp.MvpActivity
import org.devconmyanmar.devconyangon.databinding.ActivitySyncBinding
import org.devconmyanmar.devconyangon.feature.home.HomeActivity

/**
 * Created by Vincent on 12/15/19
 */
class SyncActivity : MvpActivity<ActivitySyncBinding, SyncView, SyncViewModel>(), SyncView {

  override val viewModel: SyncViewModel by contractedViewModel()

  override val binding: ActivitySyncBinding by lazy {
    ActivitySyncBinding.inflate(layoutInflater)
  }

  override fun onPostCreate(savedInstanceState: Bundle?) {
    super.onPostCreate(savedInstanceState)
    viewModel.syncData()
  }

  override fun navigateToHome() {
    val intent = Intent(this, HomeActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
    startActivity(intent)
    finish()
  }

}
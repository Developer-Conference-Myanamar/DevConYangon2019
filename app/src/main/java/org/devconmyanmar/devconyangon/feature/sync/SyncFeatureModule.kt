package org.devconmyanmar.devconyangon.feature.sync

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import org.devconmyanmar.devconyangon.base.di.viewmodel.ViewModelKey
import org.devconmyanmar.devconyangon.di.WorkerKey

/**
 * Created by Vincent on 12/15/19
 */
@Module
abstract class SyncFeatureModule {

  @ContributesAndroidInjector
  abstract fun syncActivity(): SyncActivity

  @Binds
  @IntoMap
  @ViewModelKey(SyncViewModel::class)
  abstract fun syncViewModel(syncViewModel: SyncViewModel): ViewModel

  @Binds
  @IntoMap
  @WorkerKey(SyncWorker::class)
  abstract fun syncWorker(factory: SyncWorker.Factory): ChildWorkerFactory

}
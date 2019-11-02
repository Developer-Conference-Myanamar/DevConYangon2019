package org.devconmyanmar.devconyangon.feature.schedule

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import org.devconmyanmar.devconyangon.base.di.viewmodel.ViewModelKey

/**
 * Created by Vincent on 2019-11-02
 */
@Module
abstract class ScheduleFeatureModule {

  @ContributesAndroidInjector
  abstract fun scheduleFragment(): ScheduleFragment

  @Binds
  @IntoMap
  @ViewModelKey(ScheduleViewModel::class)
  abstract fun scheduleViewModel(scheduleViewModel: ScheduleViewModel): ViewModel

}
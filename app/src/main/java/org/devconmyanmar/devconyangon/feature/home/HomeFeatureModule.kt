package org.devconmyanmar.devconyangon.feature.home

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import org.devconmyanmar.devconyangon.base.di.viewmodel.ViewModelKey
import org.devconmyanmar.devconyangon.feature.about.InfoFeatureModule
import org.devconmyanmar.devconyangon.feature.agenda.MyAgendaFeatureModule
import org.devconmyanmar.devconyangon.feature.schedule.ScheduleFeatureModule

/**
 * Created by Vincent on 2019-11-01
 */
@Module(includes = [InfoFeatureModule::class, MyAgendaFeatureModule::class, ScheduleFeatureModule::class])
abstract class HomeFeatureModule {

  @ContributesAndroidInjector
  abstract fun homeActivity(): HomeActivity

  @Binds
  @IntoMap
  @ViewModelKey(HomeViewModel::class)
  abstract fun homeViewModel(homeViewModel: HomeViewModel): ViewModel
}
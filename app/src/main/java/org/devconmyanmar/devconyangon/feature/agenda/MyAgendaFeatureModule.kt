package org.devconmyanmar.devconyangon.feature.agenda

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
abstract class MyAgendaFeatureModule {

  @ContributesAndroidInjector
  abstract fun myAgendaFragment(): MyAgendaFragment

  @Binds
  @IntoMap
  @ViewModelKey(MyAgendaViewModel::class)
  abstract fun myAgendaViewModel(myAgendaViewModel: MyAgendaViewModel): ViewModel

}
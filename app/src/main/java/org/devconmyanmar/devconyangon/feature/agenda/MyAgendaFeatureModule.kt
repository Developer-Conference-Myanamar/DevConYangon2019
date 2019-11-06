package org.devconmyanmar.devconyangon.feature.agenda

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import org.devconmyanmar.devconyangon.base.di.viewmodel.ViewModelKey
import org.devconmyanmar.devconyangon.feature.agenda.session.MyAgendaSessionFragment
import org.devconmyanmar.devconyangon.feature.agenda.session.MyAgendaSessionViewModel

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

  @ContributesAndroidInjector
  abstract fun myAgendaSessionFragment(): MyAgendaSessionFragment

  @Binds
  @IntoMap
  @ViewModelKey(MyAgendaSessionViewModel::class)
  abstract fun myAgendaSessionViewModel(myAgendaSessionViewModel: MyAgendaSessionViewModel): ViewModel

}
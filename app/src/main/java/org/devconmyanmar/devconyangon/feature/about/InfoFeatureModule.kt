package org.devconmyanmar.devconyangon.feature.about

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
abstract class InfoFeatureModule {

  @ContributesAndroidInjector
  abstract fun infoFragment(): InfoFragment

  @Binds
  @IntoMap
  @ViewModelKey(InfoViewModel::class)
  abstract fun infoViewModel(infoViewModel: InfoViewModel): ViewModel

}
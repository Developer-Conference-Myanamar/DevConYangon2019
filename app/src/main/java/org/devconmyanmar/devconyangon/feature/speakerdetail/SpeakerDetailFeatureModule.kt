package org.devconmyanmar.devconyangon.feature.speakerdetail

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import org.devconmyanmar.devconyangon.base.di.viewmodel.ViewModelKey

/**
 * Created by Vincent on 11/12/19
 */
@Module
abstract class SpeakerDetailFeatureModule {

  @ContributesAndroidInjector
  abstract fun speakerDetailFragment(): SpeakerDetailFragment

  @Binds
  @IntoMap
  @ViewModelKey(SpeakerDetailViewModel::class)
  abstract fun speakerDetailViewModel(speakerDetailViewModel: SpeakerDetailViewModel): ViewModel
}

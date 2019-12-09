package org.devconmyanmar.devconyangon.feature.speaker

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import org.devconmyanmar.devconyangon.base.di.viewmodel.ViewModelKey

@Module
abstract class SpeakerFeatureModule {

    @ContributesAndroidInjector
    abstract fun speakerActivity():SpeakerInfoActivity

    @Binds
    @IntoMap
    @ViewModelKey(value = SpeakerViewModel::class)
    abstract fun speakerViewModel(speakerViewModel: SpeakerViewModel):ViewModel
}
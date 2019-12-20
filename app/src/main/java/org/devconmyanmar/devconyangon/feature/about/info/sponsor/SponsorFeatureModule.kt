package org.devconmyanmar.devconyangon.feature.about.info.sponsor

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import org.devconmyanmar.devconyangon.base.di.viewmodel.ViewModelKey

@Module
abstract class SponsorFeatureModule {

    @ContributesAndroidInjector
    abstract fun sponsorFragment(): SponsorFragment

    @Binds
    @IntoMap
    @ViewModelKey(SponsorViewModel::class)
    abstract fun sponsorViewModel(sponsorViewModel: SponsorViewModel): ViewModel
}
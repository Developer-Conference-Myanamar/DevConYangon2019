package org.devconmyanmar.devconyangon.feature.schedule.sessiondetail

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import org.devconmyanmar.devconyangon.base.di.viewmodel.ViewModelKey

@Module
abstract class SessionDetailFeatureModule {

    @ContributesAndroidInjector
    abstract fun sessionDetailActivity():SessionDetailActivity

    @Binds
    @IntoMap
    @ViewModelKey(SessionDetailViewModel::class)
    abstract fun sessionDetailViewModel(sessionDetailViewModel: SessionDetailViewModel):ViewModel
}
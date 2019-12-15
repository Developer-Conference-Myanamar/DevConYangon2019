package org.devconmyanmar.devconyangon.feature.notification

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.devconmyanmar.devconyangon.domain.notification.SessionNotifier

/**
 * Created by Vincent on 12/15/19
 */
@Module
abstract class NotificationModule {

  @Binds
  abstract fun sessionNotifier(sessionNotifier: SessionAndroidNotifier) : SessionNotifier

  @ContributesAndroidInjector
  abstract fun notificationBroadcastReceiver() : NotificationBroadcastReceiver
}

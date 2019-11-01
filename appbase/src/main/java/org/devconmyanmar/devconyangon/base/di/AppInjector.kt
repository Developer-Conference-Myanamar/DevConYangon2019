package org.devconmyanmar.devconyangon.base.di

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection

/**
 * Created by Vincent on 2019-05-23
 */
object AppInjector {

  fun initAutoInjection(application: Application) {

    application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
      override fun onActivityPaused(p0: Activity?) {}

      override fun onActivityResumed(p0: Activity?) {}

      override fun onActivityStarted(p0: Activity?) {}

      override fun onActivityDestroyed(p0: Activity?) {}

      override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {}

      override fun onActivityStopped(p0: Activity?) {
      }

      override fun onActivityCreated(activity: Activity, p1: Bundle?) {
        handleActivity(activity)
      }

    })
  }

  private fun handleActivity(activity: Activity) {
    if (activity is Injectable) {
      AndroidInjection.inject(activity)
    }
    if (activity is FragmentActivity) {
      activity.supportFragmentManager
        .registerFragmentLifecycleCallbacks(
          object : FragmentManager.FragmentLifecycleCallbacks() {

            override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
              super.onFragmentAttached(fm, f, context)
              if (f is Injectable) {
                AndroidSupportInjection.inject(f)
              }
            }

            override fun onFragmentCreated(
              fm: FragmentManager,
              f: Fragment,
              savedInstanceState: Bundle?
            ) {

            }
          }, true
        )
    }
  }
}
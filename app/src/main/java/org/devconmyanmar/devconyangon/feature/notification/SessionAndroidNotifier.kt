package org.devconmyanmar.devconyangon.feature.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import org.devconmyanmar.devconyangon.domain.model.Session
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.notification.SessionNotifier
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import javax.inject.Inject

/**
 * Created by Vincent on 12/15/19
 */
class SessionAndroidNotifier @Inject constructor(
  private val context: Context
) : SessionNotifier {

  override fun addSessionNotification(session: Session, notificationDateTime: LocalDateTime) {
    val publisherPendingIntent = getNotificationPublisherIntent(session.sessionId)

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    val instantAtNotificationDateTime =
      notificationDateTime.atZone(ZoneId.systemDefault()).toInstant()

    alarmManager.setExact(
      AlarmManager.RTC_WAKEUP,
      instantAtNotificationDateTime.toEpochMilli(),
      publisherPendingIntent
    );
  }

  override fun removeSessionNotification(session: Session) {
    val publisherIntent = getNotificationPublisherIntent(session.sessionId)

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    alarmManager.cancel(publisherIntent)
  }

  /**
   * Generate pending Intent for cancelling/starting alarm manger
   */
  private fun getNotificationPublisherIntent(sessionId: SessionId): PendingIntent {

    val notificationIntent: Intent =
      NotificationBroadcastReceiver.newIntent(context, sessionId)

    return PendingIntent.getBroadcast(
      context, sessionId.value.toInt(), notificationIntent,
      PendingIntent.FLAG_ONE_SHOT
    )
  }
}


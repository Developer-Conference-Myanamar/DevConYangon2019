package org.devconmyanmar.devconyangon.feature.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import dagger.android.AndroidInjection
import kotlinx.coroutines.runBlocking
import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.domain.model.SessionId
import org.devconmyanmar.devconyangon.domain.usecase.GetSessionDetail
import java.util.Locale
import javax.inject.Inject

/**
 * Created by Vincent on 12/15/19
 */
class NotificationBroadcastReceiver : BroadcastReceiver() {

  companion object {

    private const val IE_SESSION_ID = "SESSION_ID"
    private const val SESSION_NOTIFICATION_CHANNEL = "SESSION_NOTIFICATION_CHANNEL"

    fun newIntent(context: Context, sessionId: SessionId): Intent {
      val intent = Intent(context, NotificationBroadcastReceiver::class.java)
      intent.putExtra(IE_SESSION_ID, sessionId.value)
      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      return intent
    }
  }

  @Inject
  lateinit var getSession: GetSessionDetail

  override fun onReceive(context: Context?, intent: Intent?) = runBlocking {
    AndroidInjection.inject(this@NotificationBroadcastReceiver, context)

    val sessionId = SessionId(intent!!.getLongExtra(IE_SESSION_ID, 0L))
    val session = getSession.execute(sessionId)

    val dateTimeFormatter =
      org.threeten.bp.format.DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH)

    val notificationTitle = session.sessionTitle

    val notificationMessage =
      "Starting at ${dateTimeFormatter.format(session.startTime)} in ${session.room.roomName}"

    if (VERSION.SDK_INT >= VERSION_CODES.O) {
      val notificationChannel = NotificationChannel(
        SESSION_NOTIFICATION_CHANNEL,
        "Session Reminder",
        NotificationManager.IMPORTANCE_DEFAULT
      )
      notificationChannel.enableLights(false)
      notificationChannel.enableVibration(false)
      notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
      (context!!.getSystemService(
        Context.NOTIFICATION_SERVICE
      ) as NotificationManager).createNotificationChannel(notificationChannel)
    }

//    val pendingIntent = NavDeepLinkBuilder(context!!)
//      .setGraph(R.navigation.navigation_main)
//      .
//      .setDestination(R.id.sessionDetailFragment)
//      .setArguments(SessionDetailFragmentArgs(sessionId.value).toBundle())
//      .createPendingIntent()

    val url = "devconyangon://sessions/${sessionId.value}"
    val sessionDetailDeepLinkIntent = Intent(Intent.ACTION_VIEW)
    sessionDetailDeepLinkIntent.data = url.toUri()
    val pendingIntent = PendingIntent.getActivity(
      context!!, sessionId.value.toInt(), sessionDetailDeepLinkIntent,
      PendingIntent.FLAG_ONE_SHOT
    )

    val notificationBuilder = NotificationCompat.Builder(context!!, SESSION_NOTIFICATION_CHANNEL)
      .setContentTitle(notificationTitle)
      .setContentInfo(notificationMessage)
      .setSmallIcon(R.drawable.ic_notification)
      .setColor(ContextCompat.getColor(context!!, R.color.colorAccent))
      .setStyle(
        NotificationCompat.BigTextStyle()
          .bigText(notificationMessage)
      )
      .setContentIntent(pendingIntent)

    val notification = notificationBuilder.build()

    val notificationManagerCompat = NotificationManagerCompat.from(context)

    notificationManagerCompat.notify(sessionId.value.toInt(), notification)
  }

}

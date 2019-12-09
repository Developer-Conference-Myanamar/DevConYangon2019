package org.devconmyanmar.devconyangon.utils.Notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.toBitmap
import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.feature.home.HomeActivity



class AlarmReceiver : BroadcastReceiver() {

    companion object {
        var NOTIFICATION_ID = "notification-id"
        var NOTIFICATION = "notification"
        var NOTIFICATION_DATA="data"

        fun sendNotification(context: Context, messageBody: String) {
            val intent = Intent(context, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            val pendingIntent = PendingIntent.getActivity(
                context, 0 /* Request code */, intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            );
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notificationChannel = NotificationChannel(
                    context.getString(R.string.devcon_notification_channel),
                    "Rewards Notifications", NotificationManager.IMPORTANCE_DEFAULT
                );

                // Configure the notification channel.
                notificationChannel.description = "Channel description";
                notificationManager.createNotificationChannel(notificationChannel)
            }

            val bigImage = BitmapFactory.decodeResource(
                context.resources,
                R.drawable.welcome_splash
            )

            val bigPictureStyle = NotificationCompat.BigPictureStyle()
                .bigPicture(bigImage)
                .bigLargeIcon(null)

            val notificationBuilder = NotificationCompat.Builder(
                context,
                context.getString(R.string.devcon_notification_channel)
            )
                .setSmallIcon(R.drawable.ic_account_circle)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setContentTitle(context.getString(R.string.devcon_notification_title))
                .setContentText(messageBody)
                .setContentIntent(pendingIntent)
                .setStyle(bigPictureStyle)
                .setAutoCancel(true)

            notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        sendNotification(
            context,
            intent.getStringExtra(NOTIFICATION_DATA)
        )
    }
}

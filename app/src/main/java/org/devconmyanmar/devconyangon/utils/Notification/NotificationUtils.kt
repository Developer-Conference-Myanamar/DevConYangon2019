/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.devconmyanmar.devconyangon.utils.Notification

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.renderscript.RenderScript
import androidx.core.app.NotificationCompat
import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.feature.home.HomeActivity

// Notification ID.
private val NOTIFICATION_ID = 0
private val REQUEST_CODE = 0
private val FLAGS = 0


fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {
    // Create the content intent for the notification, which launches
    // this activity

    val contentIntent = Intent(applicationContext, HomeActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(
        applicationContext,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )
    //TODO change notification image
    val eggImage = BitmapFactory.decodeResource(
        applicationContext.resources,
        R.drawable.ic_account_circle
    )
    val bigPictureStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(eggImage)
        .bigLargeIcon(null)

    // Build the notification
    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.devcon_notification_channel)
    )


        .setSmallIcon(R.drawable.ic_account_circle)
        .setContentTitle(applicationContext.getString(R.string.devcon_notification_title))
        .setContentText(messageBody)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .setStyle(bigPictureStyle)
        .setLargeIcon(eggImage)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        builder.priority = NotificationManager.IMPORTANCE_HIGH
    } else {
        builder.priority = NotificationCompat.PRIORITY_HIGH
    }


    notify(NOTIFICATION_ID, builder.build())
}



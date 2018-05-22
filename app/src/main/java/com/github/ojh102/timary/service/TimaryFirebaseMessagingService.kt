package com.github.ojh102.timary.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.O
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.github.ojh102.timary.BuildConfig
import com.github.ojh102.timary.R
import com.github.ojh102.timary.db.TimarySharedPreferenceManager
import com.github.ojh102.timary.ui.splash.SplashActivity
import com.github.ojh102.timary.util.KEY_SETTING_NOTIFICATION
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.android.AndroidInjection
import timber.log.Timber
import javax.inject.Inject


class TimaryFirebaseMessagingService : FirebaseMessagingService() {

    @Inject lateinit var timarySharedPreferenceManager: TimarySharedPreferenceManager

    override fun onCreate() {
        super.onCreate()
        AndroidInjection.inject(this)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        if (BuildConfig.DEBUG) {
            Timber.d("TimaryFirebaseMessagingService - From: ${remoteMessage?.from}")
            Timber.d("TimaryFirebaseMessagingService - Notification: ${remoteMessage?.notification}")
            Timber.d("TimaryFirebaseMessagingService - Data: ${remoteMessage?.data}")
        }

        val isShowingNotification = timarySharedPreferenceManager.getBoolean(KEY_SETTING_NOTIFICATION, true)

        if (remoteMessage != null && remoteMessage.data.isNotEmpty() && isShowingNotification) {
            val title = remoteMessage.data["title"]
            val body = remoteMessage.data["body"]
            sendNotification(title, body)
        }

        if (remoteMessage != null && remoteMessage.notification != null) {
            sendNotification(remoteMessage.notification?.title, remoteMessage.notification?.body)
        }
    }

    private fun sendNotification(title: String?, body: String?) {
        val id = System.currentTimeMillis().toInt()
        val intent = Intent(this, SplashActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        val pendingIntent = PendingIntent.getActivity(this,
                id,
                intent,
                PendingIntent.FLAG_ONE_SHOT
        )

        val channelId = this.getString(R.string.app_name)

        val notification = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.icon_home_off)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(ContextCompat.getColor(this, R.color.grape))
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentText(body)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(pendingIntent)
                .setStyle(NotificationCompat.BigTextStyle().bigText(body))
                .build()

        if (SDK_INT >= O) {
            createChannel(channelId)
        }

        NotificationManagerCompat.from(this).notify(id, notification)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel(channelId: String) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationChannel = NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_DEFAULT)

        notificationChannel.enableLights(true)
        notificationChannel.enableVibration(true)
        notificationChannel.lightColor = ContextCompat.getColor(this, R.color.grape)
        notificationManager.createNotificationChannel(notificationChannel)
    }

}
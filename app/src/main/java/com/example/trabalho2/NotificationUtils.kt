package com.example.trabalho2

import android.app.Activity
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

object NotificationUtils {
    val CHANNEL_ID = "default" //vai usar ou não a depender da função do android.

    @RequiresApi(Build.VERSION_CODES.O) //essa linha só pras versões superiores ao O
    private fun createNotificationChannel (context: Context){
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channelName = "Default"
        val channelDescription = "Notification channel"

        val channel = NotificationChannel(CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            .apply {
                description = channelDescription
                enableLights(true)
                lightColor = Color.GREEN
                enableVibration(true)
            }

        notificationManager.createNotificationChannel(channel)

    }

    fun simpleNotification(context: Context, photo: Photo){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannel(context)
        }
        var notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Simple notification - Photo")
            .setContentText("${photo.id} : ${photo.title}")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setColor(ActivityCompat.getColor(context, R.color.colorAccent))
            .setDefaults(Notification.DEFAULT_ALL)

        var notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(1, notificationBuilder.build())
    }
}
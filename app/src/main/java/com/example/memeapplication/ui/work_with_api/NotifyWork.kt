package com.example.memeapplication.ui.work_with_api

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import android.provider.Settings.Global.getString
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.memeapplication.R

class NotifyWork(appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams) {
    val context = appContext
    override fun doWork(): Result {
        createNotificationChannel()
        var builder = NotificationCompat.Builder(context, "1001")
            .setSmallIcon(R.drawable.ic_menu_gallery)
            .setContentTitle("My notification")
            .setContentText("Much longer text that cannot fit one line...")
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Much longer text that cannot fit one line..."))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(1002, builder.build())
        }
        return Result.success()
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "ApiInfoApp"
            val descriptionText = "You Apis're ready"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("1001", name, importance).apply {
                description = descriptionText
            }
            val notificationManager =
                context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}

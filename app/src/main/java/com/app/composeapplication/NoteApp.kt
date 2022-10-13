package com.app.composeapplication

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NoteApp :Application(){

    override fun onCreate() {
        super.onCreate()
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            val chanel = NotificationChannel(
                "download_chanel",
                "File Download",
                NotificationManager.IMPORTANCE_HIGH
            )
            val notificationmanager  =getSystemService(NotificationManager::class.java)
             notificationmanager.createNotificationChannel(chanel)
        }
    }
}
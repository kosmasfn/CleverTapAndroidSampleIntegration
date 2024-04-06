package com.kosmasfn.cttest.domain

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import com.clevertap.android.sdk.CleverTapAPI
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class CtMessageService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        message.data.apply {
            try {
                if (isNotEmpty()) {
                    val extras = Bundle()
                    for ((key, value) in this) {
                        extras.putString(key, value)
                    }
                    val info = CleverTapAPI.getNotificationInfo(extras)
                    if (info.fromCleverTap) {
                        CleverTapAPI.createNotification(applicationContext, extras)
                        CleverTapAPI.handleNotificationClicked(applicationContext, extras)
                    } else {
                        // not from CleverTap handle yourself or pass to another provider
                    }
                }
            } catch (t: Throwable) {
                Log.d("MYFCMLIST", "Error parsing FCM message", t)
            }
        }
        Log.i("notification", message.toString())
    }
}
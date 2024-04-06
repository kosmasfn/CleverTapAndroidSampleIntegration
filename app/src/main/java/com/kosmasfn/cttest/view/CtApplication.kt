package com.kosmasfn.cttest.view

import android.app.Application
import android.util.Log
import com.clevertap.android.sdk.ActivityLifecycleCallback
import com.clevertap.android.sdk.CleverTapAPI
import com.clevertap.android.sdk.displayunits.DisplayUnitListener
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnit
import dagger.hilt.android.HiltAndroidApp
import java.util.ArrayList

@HiltAndroidApp
class CtApplication : Application(){
    override fun onCreate() {
        ActivityLifecycleCallback.register(this)
        super.onCreate()
    }
}
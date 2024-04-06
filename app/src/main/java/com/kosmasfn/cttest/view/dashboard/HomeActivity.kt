package com.kosmasfn.cttest.view.dashboard

import android.Manifest
import android.app.NotificationManager
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.clevertap.android.sdk.ActivityLifecycleCallback
import com.clevertap.android.sdk.CleverTapAPI
import com.clevertap.android.sdk.PushPermissionResponseListener
import com.clevertap.android.sdk.pushnotification.CTPushNotificationListener
import com.kosmasfn.cttest.view.compose.BottomNavigationBar
import com.kosmasfn.cttest.view.compose.home.DashboardActivity
import com.kosmasfn.cttest.view.dashboard.ui.theme.CttestTheme
import com.kosmasfn.cttest.core.util.CleverTapExt
import java.util.HashMap

class HomeActivity : ComponentActivity(), PushPermissionResponseListener,
    CTPushNotificationListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CleverTapExt.requestPermissionNotification(this)
        CleverTapExt.registerPushPermission(this, this)
        requestLocationPermission()
        CleverTapAPI.getDefaultInstance(applicationContext)?.apply {
            ctPushNotificationListener = this@HomeActivity
        }
        setContent {
            CttestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BottomNavigationBar(this)
                }
            }
        }
    }

    private fun requestLocationPermission() {
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {}
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {}
                else -> {
                }
            }
        }
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        CleverTapExt.unregisterPushPermission(this, this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            CleverTapAPI.getDefaultInstance(this)?.pushNotificationClickedEvent(intent?.extras)
            CleverTapAPI.getDefaultInstance(this)?.pushNotificationViewedEvent(intent?.extras)
        }
    }

    override fun onNotificationClickedPayloadReceived(p0: HashMap<String, Any>?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            CleverTapAPI.getDefaultInstance(this)?.pushNotificationClickedEvent(intent!!.extras)
        }
    }

    override fun onPushPermissionResponse(accepted: Boolean) {
        Log.i(
            ContentValues.TAG,
            "onPushPermissionResponse :  InApp---> response() called accepted=$accepted"
        )
        if (accepted) {
            CleverTapAPI.createNotificationChannel(
                this, "CTTesting", "Testing Channel",
                "Testing Channel", NotificationManager.IMPORTANCE_HIGH, true
            )
        }
    }

    companion object {
        fun launchIntent(context: Context) {
            val intent = Intent(context, HomeActivity::class.java)
            context.startActivity(intent)
        }
    }
}

@Composable
fun DashboardView(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showSystemUi = true)
@Composable
fun DashboardViewPreview() {
    CttestTheme {
        DashboardView("Android")
    }
}
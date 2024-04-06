@file:OptIn(ExperimentalGlideComposeApi::class)

package com.kosmasfn.cttest.view.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.clevertap.android.sdk.CleverTapAPI
import com.clevertap.android.sdk.displayunits.DisplayUnitListener
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnit
import com.kosmasfn.cttest.view.theme.CttestTheme
import com.kosmasfn.cttest.core.util.CleverTapExt
import com.kosmasfn.cttest.view.dashboard.HomeActivity
import java.util.ArrayList

class SignInActivity : ComponentActivity(), DisplayUnitListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CttestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) { SignInView { onButtonClicked() } }
            }
        }
        CleverTapAPI.getDefaultInstance(this)?.setDisplayUnitListener(this)
    }

    private fun onShowNativeDisplayClicked() {
        CleverTapExt.onHomePageSelected(this)
    }
    private fun onButtonClicked() {
        CleverTapExt.onLoginActivity(this)
        HomeActivity.launchIntent(this)
        finish()
    }

    companion object {
        fun launchIntent(context: Context) {
            val intent = Intent(context, SignInActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onDisplayUnitsLoaded(unit: ArrayList<CleverTapDisplayUnit>?) {
        Log.i("displayunit", unit.toString())
        setContent { DisplayUnit(unit) }
    }
}

@Composable
fun DisplayUnit(unit: ArrayList<CleverTapDisplayUnit>?) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            unit?.forEach { content ->
                content.contents.forEach {
                    GlideImage(
                        model = it.media.toUri(),
                        contentDescription = it.title,
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable(onClick = { })
                            .fillParentMaxSize(),
                    )
                    Text(text = it.message)
                }
            }
        }
    }
}

@Composable
fun SignInView(onButtonClicked: () -> Unit) {
    var tfRegister by remember { mutableStateOf("") }
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            TextField(
                value = tfRegister,
                onValueChange = { tfRegister = it },
                label = { Text("Email") }
            )
            Button(
                onClick = {
                    onButtonClicked()
                },
                Modifier.width(200.dp),
            ) {
                Text(text = "Sign In")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SignInPreview() {
    CttestTheme {
        SignInView { }
    }
}
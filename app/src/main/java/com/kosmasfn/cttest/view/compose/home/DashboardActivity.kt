@file:OptIn(ExperimentalGlideComposeApi::class)

package com.kosmasfn.cttest.view.compose.home

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.clevertap.android.sdk.CleverTapAPI
import com.clevertap.android.sdk.displayunits.DisplayUnitListener
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnit
import com.kosmasfn.cttest.core.util.CleverTapExt
import java.util.ArrayList

class DashboardActivity : ComponentActivity(), DisplayUnitListener, HomeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { HomeScreen(null, null) }
        CleverTapAPI.getDefaultInstance(this)?.setDisplayUnitListener(this)
    }

    override fun onButtonClicked() {
        CleverTapExt.onHomePageSelected(this)
    }

    override fun onDisplayUnitsLoaded(p0: ArrayList<CleverTapDisplayUnit>?) {
        Log.i("displayunit", p0.toString())
        setContent { HomeDisplayUnit(p0) }
    }
}

@Composable
fun HomeDisplayUnit(unit: ArrayList<CleverTapDisplayUnit>?) {
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
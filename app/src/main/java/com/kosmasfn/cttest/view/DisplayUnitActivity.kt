@file:OptIn(ExperimentalGlideComposeApi::class)

package com.kosmasfn.cttest.view

import android.R
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.clevertap.android.sdk.CleverTapAPI
import com.clevertap.android.sdk.displayunits.DisplayUnitListener
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnit
import com.kosmasfn.cttest.view.ui.theme.CttestTheme
import com.kosmasfn.cttest.core.util.CleverTapExt

class DisplayUnitActivity : ComponentActivity(), DisplayUnitListener {

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initCleverTap()
        progressDialog = ProgressDialog.show(this, "Fetching data", "Please wait....")
        progressDialog.show()
    }

    private fun initCleverTap() {
        CleverTapAPI.getDefaultInstance(this)?.setDisplayUnitListener(this)
        CleverTapExt.onHomePageSelected(this)
        CleverTapExt.handleDisplayUnit(this)
    }

    override fun onDisplayUnitsLoaded(units: ArrayList<CleverTapDisplayUnit>?) {
        setContent {
            DisplayUnit(units, this) {
                finish()
            }
        }
        progressDialog.hide()
    }

    companion object {
        fun launchIntent(context: Context) {
            val intent = Intent(context, DisplayUnitActivity::class.java)
            context.startActivity(intent)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayUnit(
    units: ArrayList<CleverTapDisplayUnit>?,
    context: Context,
    onBackPressed: () -> Unit
) {
    CttestTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

                topBar = {
                    CenterAlignedTopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.primary,
                        ),
                        title = {
                            Text(
                                "Display Unit",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = { onBackPressed() }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Localized description"
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = { /* do something */ }) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = "Localized description"
                                )
                            }
                        },
                        scrollBehavior = scrollBehavior,
                    )
                },
            ) { innerPadding ->
                DisplayUnitContent(
                    padding = innerPadding,
                    units = units,
                    context = context
                )
            }
        }
    }
}

@Composable
fun DisplayUnitContent(
    padding: PaddingValues,
    units: ArrayList<CleverTapDisplayUnit>?,
    context: Context
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        contentPadding = padding
    ) {
        item {
            units?.forEach { content ->
                content.contents.forEach {
                    GlideImage(
                        model = it.media.toUri(),
                        contentDescription = it.title,
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable(onClick = {
                                CleverTapExt.onDisplayUnitClicked(context, content.unitID)
                            })
                            .fillParentMaxSize()
                            .size(100.dp),
                    )
                    Text(text = it.title)
                    Text(text = it.message)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DisplayUnitPreview() {
    CttestTheme {
        //DisplayUnit(arrayListOf(), {  })
    }
}
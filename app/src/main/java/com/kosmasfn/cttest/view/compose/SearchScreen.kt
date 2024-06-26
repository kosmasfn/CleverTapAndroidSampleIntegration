package com.kosmasfn.cttest.view.compose

import android.R
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kosmasfn.cttest.core.util.CleverTapExt
import com.kosmasfn.cttest.view.DisplayUnitActivity
import com.kosmasfn.cttest.view.theme.CttestTheme

@Composable
fun SearchScreen(navController: NavController, context: Context) {
    CttestTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = android.R.color.transparent))
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(
                    text = "Home Screen",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
                Button(onClick = {

                }) {
                    Text(text = "sdnfnsdfghlhgdsjfgsdf")
                }
            }
        }
    }
}
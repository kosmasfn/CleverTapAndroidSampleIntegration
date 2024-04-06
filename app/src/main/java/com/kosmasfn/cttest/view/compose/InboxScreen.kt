package com.kosmasfn.cttest.view.compose

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.clevertap.android.sdk.CTInboxListener
import com.clevertap.android.sdk.CleverTapAPI
import com.clevertap.android.sdk.InboxMessageButtonListener
import com.clevertap.android.sdk.InboxMessageListener
import com.clevertap.android.sdk.inbox.CTInboxMessage
import com.kosmasfn.cttest.view.theme.CttestTheme
import com.kosmasfn.cttest.core.util.CleverTapExt
import java.util.HashMap

val LocalInterface = staticCompositionLocalOf<CTInboxListener> { error("Not provided") }

class ListenerImpl : CTInboxListener {
    override fun inboxDidInitialize() {}
    override fun inboxMessagesDidUpdate() {}
}

class Activity : ComponentActivity(), InboxMessageButtonListener, InboxMessageListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { InboxScreen(null, null) }
    }

    override fun onInboxButtonClick(data: HashMap<String, String>?) {
        CleverTapAPI.getDefaultInstance(this)?.pushInboxNotificationClickedEvent((data?.keys ?: "") as String?)
    }

    override fun onInboxItemClicked(p0: CTInboxMessage?, p1: Int, p2: Int) {
        CleverTapAPI.getDefaultInstance(this)?.pushInboxNotificationClickedEvent(p0?.messageId)
    }
}

@Composable
fun InboxScreen(navController: NavController?, context: Context?) {
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
                    text = "Inbox Screen",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }
        }
    }

    val listenerImpl = remember { ListenerImpl() }
    CompositionLocalProvider(
        LocalInterface provides listenerImpl
    ) {
        val inner = LocalInterface.current
        inner.inboxDidInitialize()
        inner.inboxMessagesDidUpdate()
        context?.let { CleverTapExt.initInboxView(it) }
    }
}
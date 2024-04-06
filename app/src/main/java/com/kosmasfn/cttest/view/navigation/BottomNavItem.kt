//package com.kosmasfn.cttest.view.navigation
//
//import androidx.compose.ui.graphics.Color
//
//sealed class BottomNavItem(
//    var title: String,
//    var icon: Int,
//    var screen: String,
//    var label: String? = null,
//    var selectedContentColor: Color? = null,
//    var unselectedContentColor: Color? = null,
//    var alwaysShowLabel: Boolean? = null,
//    var selected: Boolean,
//    var onClick: ()-> Unit? = {  }
//) {
//    object Home : BottomNavItem("Home", android.R.drawable.ic_menu_view, "home", selected = true)
//    object Inbox : BottomNavItem("Inbox", android.R.drawable.ic_menu_view, "inbox", selected = false)
//    object Notification : BottomNavItem("Notification", android.R.drawable.ic_menu_view, "notification", selected = false)
//    object Account : BottomNavItem("Account", android.R.drawable.ic_menu_view, "account", selected = false)
//}
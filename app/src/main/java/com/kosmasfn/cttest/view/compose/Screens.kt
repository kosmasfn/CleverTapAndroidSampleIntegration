package com.kosmasfn.cttest.view.compose

sealed class Screens(val route : String) {
    object Home : Screens("home")
    object Search : Screens("search")
    object Inbox : Screens("inbox")
    object Profile : Screens("profile")
}
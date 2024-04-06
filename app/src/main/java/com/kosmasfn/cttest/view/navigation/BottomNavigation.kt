//package com.kosmasfn.cttest.view.navigation
//
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.currentBackStackEntryAsState
//
//@Composable
//fun BottomNavigation(
//    navController: NavHostController
//) {
//    val items = listOf(
//        BottomNavItem.Home,
//        BottomNavItem.Inbox,
//        BottomNavItem.Notification,
//        BottomNavItem.Account,
//    )
//
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route
//}

//package com.kosmasfn.cttest.view.navigation
//
//import androidx.compose.runtime.Composable
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import com.kosmasfn.cttest.view.compose.ProfileScreen
//import com.kosmasfn.cttest.view.compose.HomeScreen
//import com.kosmasfn.cttest.view.compose.InboxScreen
//import com.kosmasfn.cttest.view.compose.NotificationScreen
//
//@Composable
//fun NavigationGraph(navController: NavHostController) {
//    NavHost(navController, startDestination = BottomNavItem.Home.screen) {
//        composable(BottomNavItem.Home.screen) {
//            HomeScreen(navController)
//        }
//        composable(BottomNavItem.Inbox.screen) {
//            InboxScreen(navController)
//        }
//        composable(BottomNavItem.Notification.screen) {
//            NotificationScreen(navController)
//        }
//        composable(BottomNavItem.Account.screen) {
//            ProfileScreen(navController)
//        }
//    }
//}
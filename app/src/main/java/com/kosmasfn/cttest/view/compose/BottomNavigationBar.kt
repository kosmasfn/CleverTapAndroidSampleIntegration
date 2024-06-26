package com.kosmasfn.cttest.view.compose

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.clevertap.android.sdk.displayunits.DisplayUnitListener
import com.kosmasfn.cttest.view.compose.home.DashboardActivity
import com.kosmasfn.cttest.view.compose.home.HomeListener
import com.kosmasfn.cttest.view.compose.home.HomeScreen
import com.kosmasfn.cttest.core.util.CleverTapExt

@Composable
fun BottomNavigationBar(context: Context) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                BottomNavigationItem().bottomNavigationItems().forEachIndexed { _, navigationItem ->
                    NavigationBarItem(
                        selected = navigationItem.route == currentDestination?.route,
                        label = {
                            Text(navigationItem.label)
                        },
                        icon = {
                            Icon(
                                navigationItem.icon,
                                contentDescription = navigationItem.label
                            )
                        },
                        onClick = {
                            navController.navigate(navigationItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) {paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.Home.route,
            modifier = Modifier.padding(paddingValues = paddingValues)) {
            composable(Screens.Home.route) {
                CleverTapExt.onHomePageSelected(context)
                HomeScreen(
                    navController, context
                )
            }
            composable(Screens.Search.route) {
                CleverTapExt.onSearchPageSelected(context)
                SearchScreen(
                    navController, context
                )
            }
            composable(Screens.Inbox.route) {
                CleverTapExt.onInboxSelected(context)
                InboxScreen(
                    navController, context
                )
            }
            composable(Screens.Profile.route) {
                with(CleverTapExt){
                    pushProfile(context)
                    onProfilePageSelected(context)
                }
                ProfileScreen(
                    navController, context
                )
            }
//            composable(
//                Screens.Search.route,
//                deepLinks = listOf(navDeepLink {
//                    uriPattern = "https://kosmas.com/callback"
//                }),
//            ) { backStackEntry ->
//                SpotifyActivity.launchIntent(context)
//            }
        }
    }
}
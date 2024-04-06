//package com.kosmasfn.cttest.view.compose
//
//import android.annotation.SuppressLint
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.res.colorResource
//import androidx.navigation.compose.rememberNavController
//import com.kosmasfn.cttest.R
//import com.kosmasfn.cttest.view.navigation.BottomNavigation
//import com.kosmasfn.cttest.view.navigation.NavigationGraph
//
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Composable
//fun MainScreen() {
//    val navController = rememberNavController()
//    Scaffold(
//        bottomBar = { BottomNavigation(
//            navController = navController
//        ) }
//    ) {
//        NavigationGraph(navController = navController)
//    }
//}
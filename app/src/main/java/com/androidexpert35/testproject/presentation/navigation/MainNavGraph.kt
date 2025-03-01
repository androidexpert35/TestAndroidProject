package com.androidexpert35.testproject.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.androidexpert35.testproject.R
import com.androidexpert35.testproject.domain.entity.Item
import com.androidexpert35.testproject.presentation.ui.screen.ErrorScreen
import com.androidexpert35.testproject.presentation.ui.screen.LoadingScreen
import com.androidexpert35.testproject.presentation.ui.screen.itemdetail.DetailScreen
import com.androidexpert35.testproject.presentation.ui.screen.master.MasterScreen
import com.androidexpert35.testproject.presentation.viewmodel.MasterViewModel

@Composable
fun MainNavGraph(navController: NavHostController, viewModel: MasterViewModel = hiltViewModel()) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            MasterScreen(viewModel) { item ->
                // Navigate to detail screen with item data
                navController.currentBackStackEntry?.savedStateHandle?.set("item", item)
                navController.navigate(Screen.Detail.route)
            }
        }
        composable(Screen.Detail.route) {
            val item = navController.previousBackStackEntry?.savedStateHandle?.get<Item>("item")
            if (item != null) {
                DetailScreen(item = item, viewModel = hiltViewModel())
            } else {
                LoadingScreen()
            }
        }
    }
}
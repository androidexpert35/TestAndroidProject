package com.androidexpert35.testproject.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.androidexpert35.testproject.domain.entity.Item
import com.androidexpert35.testproject.presentation.ui.screen.DetailScreen
import com.androidexpert35.testproject.presentation.ui.screen.MasterScreen
import com.androidexpert35.testproject.presentation.viewmodel.MasterViewModel

@Composable
fun MainNavGraph(navController: NavHostController, viewModel: MasterViewModel = hiltViewModel()) {
    NavHost(navController = navController, startDestination = "master") {
        composable("master") {
            MasterScreen(viewModel) { item ->
                // Navigate to detail screen with item data
                navController.currentBackStackEntry?.savedStateHandle?.set("item", item)
                navController.navigate("detail")
            }
        }
        composable("detail") {
            val item = navController.previousBackStackEntry?.savedStateHandle?.get<Item>("item")
            if (item != null) {
                DetailScreen(item = item)
            } else {
                // Handle the case where the item is not found
            }
        }
    }
}
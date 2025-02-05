package com.androidexpert35.testproject.presentation.navigation

sealed class Screen(val route: String) {
    object Home: Screen("master")
    object Detail: Screen("detail")
}
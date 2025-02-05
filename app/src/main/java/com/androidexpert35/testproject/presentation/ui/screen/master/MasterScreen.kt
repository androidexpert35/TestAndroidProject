package com.androidexpert35.testproject.presentation.ui.screen.master

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.androidexpert35.testproject.R
import com.androidexpert35.testproject.domain.entity.Item
import com.androidexpert35.testproject.presentation.intent.MasterIntent
import com.androidexpert35.testproject.presentation.state.MasterState
import com.androidexpert35.testproject.presentation.ui.screen.ErrorScreen
import com.androidexpert35.testproject.presentation.viewmodel.MasterViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.androidexpert35.testproject.presentation.ui.screen.LoadingScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasterScreen(viewModel: MasterViewModel, onItemClick: (Item) -> Unit) {
    val state by viewModel.state.collectAsState()
    val items by viewModel.items.collectAsState()
    val refreshingState = rememberSwipeRefreshState(isRefreshing = state == MasterState.Loading)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.news_list)) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->
        SwipeRefresh(
            state = refreshingState,
            onRefresh = { viewModel.sendIntent(MasterIntent.FetchItems) },
            modifier = Modifier.padding(innerPadding)
        ) {
            when (state) {
                is MasterState.Loading -> LoadingScreen()
                is MasterState.Success -> {
                    MasterList(
                        items = items,
                        onItemClick = onItemClick,
                        onDeleteItem = { item -> viewModel.sendIntent(MasterIntent.DeleteItem(item)) }
                    )
                }
                is MasterState.Error -> ErrorScreen(message = (state as MasterState.Error).message)
            }
        }
    }
}
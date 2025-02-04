package com.androidexpert35.testproject.presentation.ui.screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.androidexpert35.testproject.domain.entity.Item
import com.androidexpert35.testproject.presentation.intent.MasterIntent
import com.androidexpert35.testproject.presentation.state.MasterState
import com.androidexpert35.testproject.presentation.viewmodel.MasterViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasterScreen(viewModel: MasterViewModel, onItemClick: (Item) -> Unit) {
    val state by viewModel.state.collectAsState()
    val items by viewModel.items.collectAsState()
    val refreshingState = rememberSwipeRefreshState(isRefreshing = state == MasterState.Loading)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Master List") },
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
                MasterState.Empty -> TODO()
            }
        }
    }
}

@Composable
fun MasterList(items: List<Item>, onItemClick: (Item) -> Unit, onDeleteItem: (Item) -> Unit) {
    val itemModifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)

    LazyColumn {
        items(items = items, key = { item -> item.mediaId }) { item ->
            ItemCard(item, onItemClick, onDeleteItem, itemModifier)
        }
    }
}

@Composable
fun ItemCard(item: Item, onItemClick: (Item) -> Unit, onDeleteItem: (Item) -> Unit, modifier: Modifier = Modifier) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            if (it == SwipeToDismissBoxValue.EndToStart) {
                onDeleteItem(item)
                true
            } else {
                false
            }
        }
    )

    SwipeToDismissBox(
        state = dismissState,
        modifier = modifier.padding(vertical = 4.dp), // Apply the modifier here
        backgroundContent = {
            val direction = dismissState.dismissDirection
            val color by animateColorAsState(
                when (dismissState.targetValue) {
                    SwipeToDismissBoxValue.EndToStart -> Color.Red
                    else -> Color.Transparent // Or your preferred background color when not swiping
                },
                label = ""
            )
            if (direction == SwipeToDismissBoxValue.EndToStart) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color)
                        .padding(horizontal = 20.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.White
                    )
                }
            }
        },
        content = {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(item) },
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(text = item.mediaTitleCustom, style = MaterialTheme.typography.headlineSmall)
                    Text(text = item.mediaDate.dateString, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    )
}


@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(message: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = message, color = Color.Red)
    }
}
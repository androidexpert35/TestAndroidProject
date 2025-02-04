package com.androidexpert35.testproject.presentation.ui.screen.master

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.androidexpert35.testproject.domain.entity.Item

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
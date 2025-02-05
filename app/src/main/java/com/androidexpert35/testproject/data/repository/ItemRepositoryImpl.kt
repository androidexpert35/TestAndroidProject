package com.androidexpert35.testproject.data.repository

import android.util.Log
import com.androidexpert35.testproject.data.remote.ItemService
import com.androidexpert35.testproject.domain.entity.Item
import com.androidexpert35.testproject.domain.repository.ItemRepository
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val itemService: ItemService
) : ItemRepository {

    private var cachedItems: List<Item> = emptyList()

    override suspend fun getItems(): List<Item> {
        try {
            val remoteItems = itemService.getItems()
            cachedItems = remoteItems
            return remoteItems
        } catch (e: Exception) {
            Log.e("ItemRepositoryImpl", "Error fetching items", e)
            // Return cached items in case of an error, or an empty list if there are no cached items
            return cachedItems
        }
    }

    override suspend fun deleteItem(item: Item) {
        cachedItems = cachedItems.toMutableList().also { it.remove(item) }
    }
}
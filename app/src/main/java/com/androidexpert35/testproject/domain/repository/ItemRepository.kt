package com.androidexpert35.testproject.domain.repository

import com.androidexpert35.testproject.domain.entity.Item

interface ItemRepository {
    suspend fun getItems(): List<Item>
    suspend fun deleteItem(item: Item)
}
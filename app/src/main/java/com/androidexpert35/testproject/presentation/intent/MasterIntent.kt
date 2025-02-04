package com.androidexpert35.testproject.presentation.intent

import com.androidexpert35.testproject.domain.entity.Item

sealed class MasterIntent {
    object FetchItems : MasterIntent()
    data class DeleteItem(val item: Item) : MasterIntent()
    data class SelectItem(val item: Item) : MasterIntent()
}
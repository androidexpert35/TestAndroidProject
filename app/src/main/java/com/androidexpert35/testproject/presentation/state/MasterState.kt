package com.androidexpert35.testproject.presentation.state

import com.androidexpert35.testproject.domain.entity.Item

sealed class MasterState {
    object Loading : MasterState()
    data class Error(val message: String) : MasterState()
    data class Success(val items: List<Item>) : MasterState()
}
package com.androidexpert35.testproject.domain.usecase

import com.androidexpert35.testproject.domain.entity.Item
import com.androidexpert35.testproject.domain.repository.ItemRepository
import javax.inject.Inject

class DeleteItemUseCase @Inject constructor(private val repository: ItemRepository) {
    suspend operator fun invoke(item: Item) {
        repository.deleteItem(item)
    }
}
package com.androidexpert35.testproject.domain.usecase

import com.androidexpert35.testproject.domain.entity.Item
import com.androidexpert35.testproject.domain.repository.ItemRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteItemUseCaseImpl @Inject constructor(private val repository: ItemRepository): DeleteItemUseCase {
    override suspend operator fun invoke(item: Item) {
        repository.deleteItem(item)
    }
}
package com.androidexpert35.testproject.domain.usecase

import com.androidexpert35.testproject.domain.entity.Item
import com.androidexpert35.testproject.domain.repository.ItemRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetItemsUseCaseImpl @Inject constructor(private val repository: ItemRepository): GetItemsUseCase {
    override suspend operator fun invoke(): List<Item> {
        return repository.getItems()
    }
}
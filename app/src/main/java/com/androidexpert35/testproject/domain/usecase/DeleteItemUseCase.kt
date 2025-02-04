package com.androidexpert35.testproject.domain.usecase

import com.androidexpert35.testproject.domain.entity.Item

interface DeleteItemUseCase {
    suspend operator fun invoke(item: Item)
}
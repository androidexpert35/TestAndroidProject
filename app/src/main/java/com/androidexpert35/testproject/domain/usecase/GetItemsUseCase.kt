package com.androidexpert35.testproject.domain.usecase

import com.androidexpert35.testproject.domain.entity.Item

interface GetItemsUseCase {
    suspend operator fun invoke(): List<Item>
}
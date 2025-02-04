package com.androidexpert35.testproject.data.di

import com.androidexpert35.testproject.domain.repository.ItemRepository
import com.androidexpert35.testproject.domain.usecase.DeleteItemUseCase
import com.androidexpert35.testproject.domain.usecase.DeleteItemUseCaseImpl
import com.androidexpert35.testproject.domain.usecase.GetItemsUseCase
import com.androidexpert35.testproject.domain.usecase.GetItemsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ItemModule {

    @Provides
    @Singleton
    fun provideGetItemsUseCase(itemRepository: ItemRepository): GetItemsUseCase {
        return GetItemsUseCaseImpl(itemRepository)
    }

    @Provides
    @Singleton
    fun deleteItemUseCase(itemRepository: ItemRepository): DeleteItemUseCase {
        return DeleteItemUseCaseImpl(itemRepository)
    }
}
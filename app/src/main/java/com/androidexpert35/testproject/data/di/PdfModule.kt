package com.androidexpert35.testproject.data.di

import com.androidexpert35.testproject.domain.usecase.LoadPdfUseCase
import com.androidexpert35.testproject.domain.usecase.LoadPdfUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PdfModule {

    @Provides
    @Singleton
    fun provideLoadPdfUseCase(): LoadPdfUseCase {
        return LoadPdfUseCaseImpl()
    }

}
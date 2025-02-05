package com.androidexpert35.testproject.domain.usecase

import android.content.Context
import android.graphics.Bitmap

interface LoadPdfUseCase {
    suspend operator fun invoke(url: String, context: Context): Result<List<Bitmap>>
}
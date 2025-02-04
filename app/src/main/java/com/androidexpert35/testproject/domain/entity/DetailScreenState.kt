package com.androidexpert35.testproject.domain.entity

import android.graphics.Bitmap

data class DetailScreenState(
    val isLoading: Boolean = false,
    val pdfBitmaps: List<Bitmap> = emptyList(),
    val error: String? = null
)
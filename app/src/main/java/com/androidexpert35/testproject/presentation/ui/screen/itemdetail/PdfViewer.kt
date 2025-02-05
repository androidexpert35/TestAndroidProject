package com.androidexpert35.testproject.presentation.ui.screen.itemdetail

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PdfViewer(pdfBitmaps: List<Bitmap>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(pdfBitmaps) { index, bitmap ->
            PdfPageItem(bitmap = bitmap, pageNumber = index + 1)
        }
    }
}
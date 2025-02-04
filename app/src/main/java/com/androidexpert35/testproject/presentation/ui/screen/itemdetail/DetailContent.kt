package com.androidexpert35.testproject.presentation.ui.screen.itemdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.androidexpert35.testproject.domain.entity.DetailScreenState
import com.androidexpert35.testproject.domain.entity.Item
import com.androidexpert35.testproject.presentation.ui.screen.ErrorScreen
import com.androidexpert35.testproject.presentation.ui.screen.LoadingScreen

@Composable
fun DetailContent(
    item: Item,
    uiState: DetailScreenState,
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
    ) {
        ItemInfoCard(item)

        Spacer(modifier = Modifier.height(16.dp))

        when {
            uiState.isLoading -> LoadingScreen()
            uiState.error != null -> ErrorScreen(uiState.error)
            item.mediaType == "pdf" && uiState.pdfBitmaps.isNotEmpty() -> PdfViewer(uiState.pdfBitmaps)
            else -> NoPreviewAvailable()
        }
    }
}
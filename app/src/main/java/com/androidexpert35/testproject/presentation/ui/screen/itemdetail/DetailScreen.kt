package com.androidexpert35.testproject.presentation.ui.screen.itemdetail

import com.androidexpert35.testproject.domain.entity.Item
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.runtime.collectAsState
import com.androidexpert35.testproject.presentation.viewmodel.DetailViewModel


@Composable
fun DetailScreen(
    item: Item,
    viewModel: DetailViewModel
) {
    val systemUiController = rememberSystemUiController()
    val context = LocalContext.current
    val materialColor =  MaterialTheme.colorScheme.primary

    SideEffect {
        systemUiController.setStatusBarColor(color = materialColor)
    }

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = item.mediaUrl, key2 = context) {
        if (item.mediaType == "pdf") {
            viewModel.loadPdf(item.mediaUrl, context)
        }
    }

    Scaffold(
        topBar = {
            PdfViewerAppBar()
        }
    ) { paddingValues ->
        DetailContent(
            item = item,
            uiState = uiState,
            paddingValues = paddingValues
        )
    }
}
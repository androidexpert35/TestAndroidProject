package com.androidexpert35.testproject.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidexpert35.testproject.presentation.state.DetailScreenState
import com.androidexpert35.testproject.domain.usecase.LoadPdfUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val loadPdfUseCase: LoadPdfUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailScreenState())
    val uiState: StateFlow<DetailScreenState> = _uiState.asStateFlow()

    fun loadPdf(url: String, context: Context) {
        _uiState.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            loadPdfUseCase(url, context)
                .onSuccess { bitmaps ->
                    _uiState.update { it.copy(isLoading = false, pdfBitmaps = bitmaps) }
                }
                .onFailure { e ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = "Failed to load PDF: ${e.message}"
                        )
                    }
                }
        }
    }
}
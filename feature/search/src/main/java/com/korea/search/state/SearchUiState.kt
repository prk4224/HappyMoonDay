package com.korea.search.state

internal sealed class SearchUiState {
    data object None : SearchUiState()
    data object Loading : SearchUiState()
    data object Success : SearchUiState()
    data object Empty : SearchUiState()
    data object Error : SearchUiState()
}

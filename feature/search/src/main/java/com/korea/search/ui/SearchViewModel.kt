package com.korea.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korea.search.domain.FetchSearchUseCase
import com.korea.search.domain.model.Artwork
import com.korea.search.domain.model.SearchParams
import com.korea.search.state.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val fetchSearchUseCase: FetchSearchUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.None)
    val uiState = _uiState.asStateFlow()

    private val _artworks = MutableStateFlow<List<Artwork>>(listOf())
    val artworks = _artworks.asStateFlow()

    private var totalCount = 0
    private var searchKeyword = ""
    private var startIndex = 0

    fun fetch(keyword: String) {
        viewModelScope.launch {
            _uiState.value = SearchUiState.Loading

            searchKeyword = keyword

            val params = SearchParams(
                startIndex = startIndex,
                endIndex = startIndex + PAGE_SIZE,
                productClassName = "조각",
                manufactureYear = null,
                productNameKorean = keyword,
                productNameEnglish = null
            )

            fetchSearchUseCase(params)
                .onSuccess { item ->
                    if (item.totalCount == 0) {
                        _uiState.value = SearchUiState.Empty
                    } else {
                        _artworks.value = item.artworks
                        startIndex += PAGE_SIZE + 1
                        totalCount = item.totalCount
                        _uiState.value = SearchUiState.Success
                    }
                }
                .onFailure {
                    _uiState.value = SearchUiState.Error
                }
        }
    }

    fun more() {
        viewModelScope.launch {
            if (totalCount < startIndex) return@launch

            val params = SearchParams(
                startIndex = startIndex,
                endIndex = startIndex + PAGE_SIZE,
                productClassName = "",
                manufactureYear = null,
                productNameKorean = searchKeyword,
                productNameEnglish = null
            )

            fetchSearchUseCase(params)
                .onSuccess { item ->
                    val newArtwork = artworks.value + item.artworks
                    _artworks.value = newArtwork
                    startIndex += PAGE_SIZE + 1
                }
        }
    }

    companion object {
        const val PAGE_SIZE = 100
    }
}

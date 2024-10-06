package com.korea.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korea.search.dialog.model.ManuFactureYearSort
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

    private val _selectedManuFactureYear = MutableStateFlow(SORT_BY_YEAR_ASC)
    val selectedManuFactureYear = _selectedManuFactureYear.asStateFlow()

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
                productClassName = "",
                manufactureYear = null,
                productNameKorean = keyword,
                productNameEnglish = null
            )

            fetchSearchUseCase(params)
                .onSuccess { item ->
                    if (item.totalCount == 0) {
                        _uiState.value = SearchUiState.Empty
                    } else {
                        updateArtworks(item.artworks)
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

    private fun updateArtworks(artworks: List<Artwork>) {
        if (selectedManuFactureYear.value == SORT_BY_YEAR_ASC) {
            _artworks.value = artworks.sortedBy {
                it.manuFactureYear
            }
        } else {
            _artworks.value = artworks.sortedByDescending {
                it.manuFactureYear
            }
        }
    }

    fun makeManuFactureYearList(): List<ManuFactureYearSort> {
        return listOf(
            ManuFactureYearSort(
                title = SORT_BY_YEAR_ASC,
                isSelected = selectedManuFactureYear.value == SORT_BY_YEAR_ASC
            ),
            ManuFactureYearSort(
                title = SORT_BY_YEAR_DESC,
                isSelected = selectedManuFactureYear.value == SORT_BY_YEAR_DESC
            )
        )
    }

    fun updateManuFactureYearSort(manuFactureYearSort: ManuFactureYearSort) {
        fetch(searchKeyword)
        _selectedManuFactureYear.value = manuFactureYearSort.title
    }

    companion object {
        const val PAGE_SIZE = 20
        const val SORT_BY_YEAR_ASC = "제작년도 오름차순"
        const val SORT_BY_YEAR_DESC = "제작년도 내림차순"
    }
}

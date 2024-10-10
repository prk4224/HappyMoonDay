package com.korea.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korea.search.dialog.model.BottomSheetItem
import com.korea.search.domain.FetchSearchUseCase
import com.korea.search.domain.model.SearchArtwork
import com.korea.search.domain.model.SearchParams
import com.korea.search.state.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SearchViewModel @Inject constructor(
    private val fetchSearchUseCase: FetchSearchUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.None)
    val uiState = _uiState.asStateFlow()

    private val _artworks = MutableStateFlow<List<SearchArtwork>>(listOf())
    val artworks = _artworks.asStateFlow()

    private val _selectedManufactureYear = MutableStateFlow(SORT_BY_YEAR_ASC)
    val selectedManufactureYear = _selectedManufactureYear.asStateFlow()

    private val _selectedProductClassNames = MutableStateFlow<List<String>>(listOf())
    val selectedProductClassNames = _selectedProductClassNames.asStateFlow()

    private var totalCount = 0
    private var searchKeyword = ""
    private var startIndex = 0
    private var originArtworks: List<SearchArtwork> = listOf()
    private val isFilterDisabled
        get() = selectedProductClassNames.value.isEmpty() ||
                selectedProductClassNames.value.contains(PRODUCT_CLASS_NAME_ALL)

    fun fetch(keyword: String) {
        viewModelScope.launch {
            _uiState.value = SearchUiState.Loading

            searchKeyword = keyword

            val params = SearchParams(
                startIndex = startIndex,
                endIndex = startIndex + PAGE_SIZE,
                productClassName = null,
                manufactureYear = null,
                productNameKorean = keyword,
                productNameEnglish = null
            )

            fetchSearchUseCase(params)
                .onSuccess { item ->
                    if (item.totalCount == 0) {
                        _artworks.value = listOf()
                        _uiState.value = SearchUiState.Empty
                    } else {
                        handleSearch(
                            searchArtworks = item.searchArtworks,
                            total = totalCount
                        )
                        _uiState.value = SearchUiState.Success
                    }
                }
                .onFailure {
                    _artworks.value = listOf()
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
                productClassName = null,
                manufactureYear = null,
                productNameKorean = searchKeyword,
                productNameEnglish = null
            )

            fetchSearchUseCase(params)
                .onSuccess { item ->
                    handleSearch(
                        searchArtworks = originArtworks + item.searchArtworks,
                        total = totalCount
                    )
                }
        }
    }

    private fun handleSearch(
        searchArtworks: List<SearchArtwork>,
        total: Int? = null,
    ) {
        originArtworks = searchArtworks
        startIndex += PAGE_SIZE + 1
        updateArtworks()
        totalCount = total ?: return
    }

    private fun updateArtworks() {
        if (selectedManufactureYear.value == SORT_BY_YEAR_ASC) {
            val artworkSort = originArtworks.sortedBy { artwork ->
                artwork.manufactureYear
            }
            updateArtworksFilter(artworkSort)
        } else {
            val artworkSort = originArtworks.sortedByDescending { artwork ->
                artwork.manufactureYear
            }
            updateArtworksFilter(artworkSort)
        }
    }

    private fun updateArtworksFilter(artworks: List<SearchArtwork>) {
        if (isFilterDisabled) {
            _artworks.value = artworks
        } else {
            _artworks.value = artworks.filter { artwork ->
                selectedProductClassNames.value.contains(artwork.productClassName)
            }
        }
    }

    fun makeManufactureYears(): List<BottomSheetItem> {
        return listOf(
            BottomSheetItem.ManufactureYear(
                title = SORT_BY_YEAR_ASC,
                isSelected = selectedManufactureYear.value == SORT_BY_YEAR_ASC
            ),
            BottomSheetItem.ManufactureYear(
                title = SORT_BY_YEAR_DESC,
                isSelected = selectedManufactureYear.value == SORT_BY_YEAR_DESC
            )
        )
    }

    fun makeProductClassName(): List<BottomSheetItem> {
        return List(PRODUCT_CLASS_NAMES.size) { index ->
            BottomSheetItem.ProductClassName(
                title = PRODUCT_CLASS_NAMES[index],
                isSelected = selectedProductClassNames.value.contains(PRODUCT_CLASS_NAMES[index])
            )
        }
    }

    fun updateManufactureYearSort(bottomSheetItem: BottomSheetItem) {
        _selectedManufactureYear.value = bottomSheetItem.title
        updateArtworks()
    }

    fun updateProductClassName(items: List<BottomSheetItem>) {
        _selectedProductClassNames.value = items.map { item -> item.title }
        updateArtworks()
    }


    companion object {
        const val PAGE_SIZE = 100
        const val SORT_BY_YEAR_ASC = "제작년도 오름차순"
        const val SORT_BY_YEAR_DESC = "제작년도 내림차순"
        const val PRODUCT_CLASS_NAME_ALL = "전체"
        val PRODUCT_CLASS_NAMES = listOf(
            "전체",
            "회화",
            "한국화",

            "드로잉&판화",
            "조각",
            "뉴미디어",
            "사진",
            "설치",
            "디자인",
        )
    }
}

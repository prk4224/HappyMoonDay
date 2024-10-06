package com.korea.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korea.search.domain.FetchSearchUseCase
import com.korea.search.domain.model.Artwork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val fetchSearchUseCase: FetchSearchUseCase,
) : ViewModel() {

    private val _artworks = MutableStateFlow<List<Artwork>>(listOf())
    val artworks = _artworks.asStateFlow()

    fun fetch() {
        viewModelScope.launch {
            fetchSearchUseCase()
                .onSuccess { item ->
                    _artworks.value = item
                }
                .onFailure {

                }
        }
    }
}

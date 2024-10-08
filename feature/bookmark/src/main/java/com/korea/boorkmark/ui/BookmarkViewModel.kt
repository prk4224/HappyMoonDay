package com.korea.boorkmark.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korea.bookmark.domain.FetchBookmarkUseCase
import com.korea.bookmark.model.BookmarkArtwork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class BookmarkViewModel @Inject constructor(
    private val fetchBookmarkUseCase: FetchBookmarkUseCase
): ViewModel() {
    private val _artworks = MutableStateFlow<List<BookmarkArtwork>>(listOf())
    val artworks = _artworks.asStateFlow()

    fun fetch() {
        viewModelScope.launch {
            fetchBookmarkUseCase().collect { items ->
                _artworks.value = items
            }
        }
    }
}

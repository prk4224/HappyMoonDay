package com.korea.boorkmark.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korea.bookmark.domain.FetchBookmarkUseCase
import com.korea.bookmark.model.BookmarkArtwork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class BookmarkViewModel @Inject constructor(
    fetchBookmarkUseCase: FetchBookmarkUseCase,
) : ViewModel() {

    val artworks: StateFlow<List<BookmarkArtwork>> = fetchBookmarkUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = listOf(),
        )
}

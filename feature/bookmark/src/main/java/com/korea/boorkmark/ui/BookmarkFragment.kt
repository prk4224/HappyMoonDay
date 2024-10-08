package com.korea.boorkmark.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.korea.bookmark.model.BookmarkArtwork
import com.korea.boorkmark.databinding.FragmentBookmarkBinding
import com.korea.common.constants.IntentKeyConstants.ARTWORK
import com.korea.common.constants.PackageNameConstants.PRODUCT_DETAIL_PACKAGE_NAME
import com.korea.common.model.Artwork
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
internal class BookmarkFragment : Fragment() {

    private var _binding: FragmentBookmarkBinding? = null
    val binding get() = checkNotNull(_binding)

    private val viewModel: BookmarkViewModel by viewModels()

    private val bookmarkAdapter by lazy {
        BookmarkAdapter(
            onClick = { bookmarkArtwork ->
                moveProductDetail(bookmarkArtwork)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.artworksRv.adapter = bookmarkAdapter
        observeViewModelData()
    }

    private fun observeViewModelData() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.artworks.collect { artwork ->
                        bookmarkAdapter.submitList(artwork)
                    }
                }
            }
        }
    }

    private fun moveProductDetail(bookmarkArtwork: BookmarkArtwork) {
        val activity = activity ?: return
        val intent = Intent().apply {
            setClassName(activity, PRODUCT_DETAIL_PACKAGE_NAME)
            putExtra(ARTWORK, bookmarkArtwork.convertToArtwork())
        }
        startActivity(intent)
    }

    private fun BookmarkArtwork.convertToArtwork(): Artwork {
        return Artwork(
            imageUrl = imageUrl,
            title = title,
            titleEnglish = titleEnglish,
            writer = writer,
            manufactureYear = manufactureYear,
            productClassName = productClassName,
            productStandard = productStandard,
            manageNoYear = manageNoYear,
            materialTechnic = materialTechnic,
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

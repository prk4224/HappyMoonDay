package com.korea.search.ui

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.korea.search.R
import com.korea.search.databinding.ActivitySearchBinding
import com.korea.search.dialog.BottomSheetDialog
import com.korea.search.state.SearchUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
internal class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val viewModel: SearchViewModel by viewModels()
    private val searchAdapter: SearchAdapter by lazy {
        SearchAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeViewModelData()
        applySearchRecyclerView()
        setupClickListeners()
    }

    private fun observeViewModelData() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.artworks.collect { artwork ->
                        searchAdapter.submitList(artwork)
                    }
                }
                launch {
                    viewModel.uiState.collect { uiState ->
                        updateUIBasedOnState(uiState)
                    }
                }

                launch {
                    viewModel.selectedManufactureYear.collect { manufactureYear ->
                        binding.filterManufactureYearTv.text = manufactureYear
                        binding.artworksRv.scrollToPosition(0)
                    }
                }
            }
        }
    }

    private fun updateUIBasedOnState(uiState: SearchUiState) = with(binding) {
        when (uiState) {
            is SearchUiState.None -> {
                progressBar.isVisible = false
                emptyGroup.isVisible = false
            }

            is SearchUiState.Loading -> {
                progressBar.isVisible = true
                emptyGroup.isVisible = false
            }

            is SearchUiState.Success -> {
                binding.progressBar.isVisible = false
                binding.emptyGroup.isVisible = false
            }

            is SearchUiState.Empty -> {
                progressBar.isVisible = false
                emptyTitleTv.text = resources.getString(R.string.empty_title, searchEt.text)
                emptyGroup.isVisible = true
            }

            is SearchUiState.Error -> {
                progressBar.isVisible = false
                emptyTitleTv.text = getString(R.string.error)
                emptyTitleTv.isVisible = true
            }
        }
    }

    private fun applySearchRecyclerView() = with(binding.artworksRv) {
        val onScrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                setScrollListener(recyclerView)
            }
        }

        adapter = searchAdapter
        addOnScrollListener(onScrollListener)
    }

    private fun setScrollListener(recyclerView: RecyclerView) {
        val newPagePointItemVisible =
            (recyclerView.layoutManager as? LinearLayoutManager)?.findLastVisibleItemPosition() ?: 0

        val itemTotalCount = searchAdapter.itemCount - NEW_PAGE_POINT

        if (isLoadNewItem(newPagePointItemVisible, itemTotalCount)) {
            viewModel.more()
        }
    }

    private fun isLoadNewItem(position: Int?, point: Int): Boolean {
        return position == point
    }

    private fun setupClickListeners() = with(binding) {
        searchTv.setOnClickListener {
            onSearchClick()
        }

        backIv.setOnClickListener {
            onBackClick()
        }

        filterManufactureYearTv.setOnClickListener {
            showBottomSheetDialog()
        }
    }

    private fun onSearchClick() {
        val keyword = binding.searchEt.text
        if (keyword.isEmpty()) return
        viewModel.fetch(keyword.toString())
        hideKeyboard()
    }

    private fun onBackClick() {
        finish()
    }

    private fun showBottomSheetDialog() {
        BottomSheetDialog(
            items = viewModel.makeManufactureYearList(),
            onClick = { manufactureYearSort ->
                viewModel.updateManufactureYearSort(manufactureYearSort)
            }
        ).show(supportFragmentManager, "")
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            currentFocus?.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    companion object {
        const val NEW_PAGE_POINT = 5
    }
}

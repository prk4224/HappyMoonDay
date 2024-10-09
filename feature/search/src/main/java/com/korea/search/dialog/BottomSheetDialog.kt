package com.korea.search.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.korea.search.databinding.BottomSheetDialogBinding
import com.korea.search.dialog.model.BottomSheetItem

internal class BottomSheetDialog(
    private val items: List<BottomSheetItem>,
    private val onClickSort: (BottomSheetItem) -> Unit,
    private val onClickFilter: (List<BottomSheetItem>) -> Unit,
) : BottomSheetDialogFragment() {

    private var _binding: BottomSheetDialogBinding? = null
    private val binding get() = checkNotNull(_binding)

    private val checkedProductClassNames: MutableList<BottomSheetItem> = mutableListOf()

    private val bottomSheetAdapter by lazy {
        BottomSheetAdapter(
            onClickSort = onClickSort,
            onClickFilter = { item, isChecked ->
                if (isChecked) {
                    checkedProductClassNames.add(item)
                } else {
                    checkedProductClassNames.remove(item)
                }
            },
            dismissDialog = {
                dismiss()
            },
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = BottomSheetDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        binding.confirmButtonTv.isVisible = items.first() is BottomSheetItem.ProductClassName
        binding.confirmButtonTv.setOnClickListener {
            onClickFilter(checkedProductClassNames)
            dismiss()
        }

        checkedProductClassNames.addAll(makeCheckPreviousItems())
    }

    private fun setRecyclerView() {
        binding.itemListRv.adapter = bottomSheetAdapter
        bottomSheetAdapter.submitList(items)
    }

    private fun makeCheckPreviousItems(): List<BottomSheetItem> {
        return items.filter { item ->
            val productClassName = item as? BottomSheetItem.ProductClassName ?: return@filter false
            productClassName.isSelected
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

package com.korea.search.dialog

import androidx.recyclerview.widget.RecyclerView
import com.korea.search.databinding.ManufactureYearItemBinding
import com.korea.search.dialog.model.BottomSheetItem

class ManuFactureYearViewHolder(
    private val binding: ManufactureYearItemBinding,
    private val onClick: (BottomSheetItem) -> Unit,
    private val dismissDialog: () -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(bottomSheetItem: BottomSheetItem) {
        binding.manufactureYearSortTv.text = bottomSheetItem.title
        binding.manufactureYearSortTv.isEnabled = bottomSheetItem.isSelected
        binding.root.setOnClickListener {
            onClick(bottomSheetItem)
            dismissDialog()
        }
    }
}

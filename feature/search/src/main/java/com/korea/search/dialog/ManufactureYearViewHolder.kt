package com.korea.search.dialog

import androidx.recyclerview.widget.RecyclerView
import com.korea.search.databinding.ManufactureYearItemBinding
import com.korea.search.dialog.model.BottomSheetItem

internal class ManufactureYearViewHolder(
    private val binding: ManufactureYearItemBinding,
    private val onClick: (BottomSheetItem) -> Unit,
    private val dismissDialog: () -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(manufactureYear: BottomSheetItem.ManufactureYear) = with(binding) {
        manufactureYearSortTv.text = manufactureYear.title
        manufactureYearSortTv.isEnabled = manufactureYear.isSelected
        root.setOnClickListener {
            onClick(manufactureYear)
            dismissDialog()
        }
    }
}

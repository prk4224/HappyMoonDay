package com.korea.search.dialog

import androidx.recyclerview.widget.RecyclerView
import com.korea.search.databinding.ManufactureYearItemBinding
import com.korea.search.dialog.model.ManuFactureYearSort

class ManuFactureYearViewHolder(
    private val binding: ManufactureYearItemBinding,
    private val onClick: (ManuFactureYearSort) -> Unit,
    private val dismissDialog: () -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(manuFactureYearSort: ManuFactureYearSort) {
        binding.manufactureYearSortTv.text = manuFactureYearSort.title
        binding.manufactureYearSortTv.isEnabled = manuFactureYearSort.isSelected
        binding.root.setOnClickListener {
            onClick(manuFactureYearSort)
            dismissDialog()
        }
    }
}

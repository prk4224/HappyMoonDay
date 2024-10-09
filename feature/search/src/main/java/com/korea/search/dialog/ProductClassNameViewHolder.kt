package com.korea.search.dialog

import androidx.recyclerview.widget.RecyclerView
import com.korea.search.databinding.ProductClassNameItemBinding
import com.korea.search.dialog.model.BottomSheetItem

internal class ProductClassNameViewHolder(
    private val binding: ProductClassNameItemBinding,
    private val onClick: (BottomSheetItem, isChecked: Boolean) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(productClassName: BottomSheetItem.ProductClassName) = with(binding) {
        productClassNameTv.text = productClassName.title
        checkBox.isChecked = productClassName.isSelected
        checkBox.setOnClickListener {
            onClick(productClassName, binding.checkBox.isChecked)
        }
    }
}

package com.korea.search.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.korea.search.databinding.EmptyItemBinding
import com.korea.search.databinding.ManufactureYearItemBinding
import com.korea.search.databinding.ProductClassNameItemBinding
import com.korea.search.dialog.model.BottomSheetItem

internal class BottomSheetAdapter(
    private val onClickSort: (BottomSheetItem) -> Unit,
    private val onClickFilter: (BottomSheetItem, isChecked: Boolean) -> Unit,
    private val dismissDialog: () -> Unit,
) : ListAdapter<BottomSheetItem, RecyclerView.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MANUFACTURE_YEAR -> ManufactureYearViewHolder(
                ManufactureYearItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                onClick = onClickSort,
                dismissDialog = dismissDialog
            )

            PRODUCT_CLASS_NAME -> ProductClassNameViewHolder(
                ProductClassNameItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                onClick = onClickFilter
            )

            else -> {
                EmptyViewHolder(
                    EmptyItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ManufactureYearViewHolder -> {
                val manufactureYear =
                    getItem(position) as? BottomSheetItem.ManufactureYear ?: return
                holder.bind(manufactureYear)
            }

            is ProductClassNameViewHolder -> {
                val productClassName =
                    getItem(position) as? BottomSheetItem.ProductClassName ?: return
                holder.bind(productClassName)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is BottomSheetItem.ManufactureYear -> MANUFACTURE_YEAR
            is BottomSheetItem.ProductClassName -> PRODUCT_CLASS_NAME
        }
    }

    private companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<BottomSheetItem>() {
            override fun areItemsTheSame(
                oldItem: BottomSheetItem,
                newItem: BottomSheetItem,
            ): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: BottomSheetItem,
                newItem: BottomSheetItem,
            ): Boolean {
                return oldItem == newItem
            }
        }

        private const val MANUFACTURE_YEAR = 0
        private const val PRODUCT_CLASS_NAME = 1
    }
}

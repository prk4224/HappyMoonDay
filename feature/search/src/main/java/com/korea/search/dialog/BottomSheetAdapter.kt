package com.korea.search.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.korea.search.databinding.ManufactureYearItemBinding
import com.korea.search.dialog.model.BottomSheetItem

class BottomSheetAdapter(
    private val onClick: (BottomSheetItem) -> Unit,
    private val dismissDialog: () -> Unit,
) : ListAdapter<BottomSheetItem, RecyclerView.ViewHolder>(diffUtil) {

    companion object {
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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ManuFactureYearViewHolder(
            ManufactureYearItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick = onClick,
            dismissDialog = dismissDialog
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ManuFactureYearViewHolder -> {
                holder.bind(getItem(position))
            }
        }
    }
}

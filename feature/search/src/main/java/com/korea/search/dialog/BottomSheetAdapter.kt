package com.korea.search.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.korea.search.databinding.ManufactureYearItemBinding
import com.korea.search.dialog.model.ManuFactureYearSort

class BottomSheetAdapter(
    private val onClick: (ManuFactureYearSort) -> Unit,
    private val dismissDialog: () -> Unit,
) : ListAdapter<ManuFactureYearSort, RecyclerView.ViewHolder>(diffUtil) {

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<ManuFactureYearSort>() {
            override fun areItemsTheSame(
                oldItem: ManuFactureYearSort,
                newItem: ManuFactureYearSort,
            ): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: ManuFactureYearSort,
                newItem: ManuFactureYearSort,
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

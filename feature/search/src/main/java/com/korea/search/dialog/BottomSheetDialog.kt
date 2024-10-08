package com.korea.search.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.korea.search.databinding.BottomSheetDialogBinding
import com.korea.search.dialog.model.BottomSheetItem

internal class BottomSheetDialog(
    private val items: List<BottomSheetItem>,
    private val onClick: (BottomSheetItem) -> Unit,
) : BottomSheetDialogFragment() {

    private var _binding: BottomSheetDialogBinding? = null
    val binding get() = checkNotNull(_binding)

    private val bottomSheetAdapter by lazy {
        BottomSheetAdapter(
            onClick = onClick,
            dismissDialog = {
                dismiss()
            }
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

    }

    private fun setRecyclerView() {
        binding.itemListRv.adapter = bottomSheetAdapter
        bottomSheetAdapter.submitList(items)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

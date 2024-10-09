package com.korea.search.dialog.model

sealed class BottomSheetItem(
    open val title: String,
) {
    data class ManufactureYear(
        override val title: String,
        val isSelected: Boolean,
    ) : BottomSheetItem(title)

    data class ProductClassName(
        override val title: String,
        val isSelected: Boolean,
    ) : BottomSheetItem(title)
}

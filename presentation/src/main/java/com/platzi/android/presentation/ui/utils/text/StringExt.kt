package com.platzi.android.presentation.ui.utils.text

fun String?.isNotNullAndBlank(block: (String) -> Unit, elseBlock: ((String?) -> Unit)? = null) {
    if (!isNullOrBlank()) {
        block(this)
    } else {
        elseBlock?.invoke(this)
    }
}

package com.kmp.Model

import androidx.compose.ui.graphics.vector.ImageVector

data class TranspostModel(
    val icon: ImageVector,
    val time: String,
    val isShortest: Boolean = false
)
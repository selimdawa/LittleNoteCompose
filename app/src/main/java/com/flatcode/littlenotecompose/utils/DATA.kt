package com.flatcode.littlenotecompose.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import io.selimdawa.multicolors.MultiColorCompose

object DATA {
    // Themes Multi-Colors
    val MC_BG @Composable get() = MultiColorCompose.mc_bg
    val MC_TRACK @Composable get() = MultiColorCompose.mc_track
    //val MC_TICK @Composable get() = MultiColorCompose.mc_tick
    //val MC_CENTER @Composable get() = MultiColorCompose.mc_center
    //val PRIMARY @Composable get() = MultiColorCompose.colorPrimary
    val COLOR_ON_BACKGROUND @Composable get() = MultiColorCompose.colorOnBackground
    val COLOR_ERROR @Composable get() = MultiColorCompose.colorError

    const val PARENT_PATH = "notes"
    const val CHILD_PATH = "myNotes"
    const val DELAY_LOG = 2000
    const val EDIT = "Edit"
    const val DELETE = "Delete"
    const val ERR_PASS = "Password Do not Match."

    val NOTE_COLORS = listOf(
        Color(0x8FFFFFF0), Color(0x99FFFFFF), Color(0xB3FFFFFF), Color(0xCCFFFFFF), Color(0xE6FFFFFF),
        Color(0xFFCCCCCC), Color(0xFF999999), Color(0xFFFFCDD2), Color(0xFFEF9A9A), Color(0xFFE57373),
        Color(0xFFFF8A80), Color(0xFFFF5252), Color(0xFFD1C4E9), Color(0xFFB39DDB), Color(0xFF9575CD),
        Color(0xFFB388FF), Color(0xFF7C4DFF), Color(0xFFB3E5FC), Color(0xFF81D4FA), Color(0xFF4FC3F7),
        Color(0xFF80D8FF), Color(0xFF40C4FF), Color(0xFFC8E6C9), Color(0xFFA5D6A7), Color(0xFF81C784),
        Color(0xFFB9F6CA), Color(0xFF69F0AE), Color(0xFFFFF59D), Color(0xFFFFF176), Color(0xFFFFFF8D),
        Color(0xFFFFFF00), Color(0xFFFFCCBC), Color(0xFFFFAB91), Color(0xFFFF8A65), Color(0xFFFF9E80),
        Color(0xFFFF6E40), Color(0xFFCFD8DC), Color(0xFFB0BEC5), Color(0xFF90A4AE), Color(0xFFF8BBD0),
        Color(0xFFF48FB1), Color(0xFFF06292), Color(0xFFFF80AB), Color(0xFFFF4081), Color(0xFFC5CAE9),
        Color(0xFF9FA8DA), Color(0xFF7986CB), Color(0xFF8C9EFF), Color(0xFF536DFE), Color(0xFFB2EBF2),
        Color(0xFF80DEEA), Color(0xFF4DD0E1), Color(0xFF26C6DA), Color(0xFF84FFFF), Color(0xFF18FFFF),
        Color(0xFF00E5FF), Color(0xFF00B8D4), Color(0xFFC5E1A5), Color(0xFFAED581), Color(0xFFCCFF90),
        Color(0xFFB2FF59), Color(0xFF76FF03), Color(0xFF64DD17), Color(0xFFFFE082), Color(0xFFFFD54F),
        Color(0xFFFFCA28), Color(0xFFFFC107), Color(0xFFFFB300), Color(0xFFFFA000), Color(0xFFFFE57F),
        Color(0xFFFFD740), Color(0xFFFFC400), Color(0xFFD7CCC8), Color(0xFFBCAAA4), Color(0xFFA1887F),
        Color(0xFFE1BEE7), Color(0xFFCE93D8), Color(0xFFBA68C8), Color(0xFFEA80FC), Color(0xFFE040FB),
        Color(0xFFBBDEFB), Color(0xFF90CAF9), Color(0xFF64B5F6), Color(0xFF42A5F5), Color(0xFF82B1FF),
        Color(0xFF448AFF), Color(0xFFB2DFDB), Color(0xFF80CBC4), Color(0xFF4DB6AC), Color(0xFF26A69A),
        Color(0xFFA7FFEB), Color(0xFF64FFDA), Color(0xFF1DE9B6), Color(0xFFE6EE9C), Color(0xFFDCE775),
        Color(0xFFD4E157), Color(0xFFCDDC39), Color(0xFFF4FF81), Color(0xFFEEFF41), Color(0xFFC6FF00),
        Color(0xFFAEEA00), Color(0xFFFFE0B2), Color(0xFFFFCC80), Color(0xFFFFB74D), Color(0xFFFFD180),
        Color(0xFFFFAB40), Color(0xFFE0E0E0)
    )

    val randomColor: Color
        get() = NOTE_COLORS.random()
}
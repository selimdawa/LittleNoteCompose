package com.flatcode.littlenote.utils

import androidx.compose.runtime.Composable
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
}
package com.flatcode.littlenote.utils

import androidx.compose.runtime.Composable
import com.flatcode.littlenote.R
import com.google.firebase.FirebaseApp
import io.selimdawa.multicolors.MultiColorCompose
import kotlin.random.Random

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
    const val NOTE = "note"

    const val COLOR = "code"
    const val DEFAULT_COLOR = 0
    const val DELAY_LOG = 2000
    const val EDIT = "Edit"
    const val DELETE = "Delete"
    const val ERR_PASS = "Password Do not Match."

    val randomColor: Int
        get() {
            val context = FirebaseApp.getInstance().applicationContext
            val typedArray = context.resources.obtainTypedArray(R.array.note_colors)
            val index = Random.nextInt(typedArray.length())
            val colorResId = typedArray.getResourceId(index, R.color.color1)
            typedArray.recycle()
            return colorResId
        }
}
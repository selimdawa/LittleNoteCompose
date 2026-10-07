package com.flatcode.littlenote.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.core.graphics.createBitmap
import androidx.core.graphics.scale
//import coil3.size.Size
//import coil3.transform.Transformation
import java.util.Locale
import kotlin.math.log10
import kotlin.math.pow

inline fun <reified T : Activity> Context.launchActivity(
    finish: Boolean = false, block: Intent.() -> Unit = {}
) {
    val intent = Intent(this, T::class.java).apply(block)
    startActivity(intent)
    if (finish && this is Activity) finish()
}

fun Context.launchActivity(
    activityClass: Class<out Activity>?, finish: Boolean = false, block: Intent.() -> Unit = {}
) {
    if (activityClass == null) return
    val intent = Intent(this, activityClass).apply(block)
    startActivity(intent)
    if (finish && this is Activity) finish()
}
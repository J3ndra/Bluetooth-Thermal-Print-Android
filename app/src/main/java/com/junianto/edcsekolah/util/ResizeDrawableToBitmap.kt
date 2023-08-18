package com.junianto.edcsekolah.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap

fun resizeDrawableToBitmap(context: Context, drawableResId: Int, targetSize: Int): Bitmap {
    // Step 1: Load the Drawable image
    val drawable: Drawable = AppCompatResources.getDrawable(context, drawableResId)
        ?: throw IllegalArgumentException("Drawable not found")

    // Step 2: Convert the Drawable to a Bitmap
    val bitmap: Bitmap = drawable.toBitmap()

    // Step 3: Resize the Bitmap to the desired dimensions

    return Bitmap.createScaledBitmap(bitmap, targetSize, targetSize, false)
}


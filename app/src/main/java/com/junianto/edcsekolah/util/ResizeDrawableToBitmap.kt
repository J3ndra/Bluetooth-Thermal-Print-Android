package com.junianto.edcsekolah.util

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.graphics.drawable.toBitmap
import com.junianto.edcsekolah.R

fun loadAndResizeBitmap(context: Context, schoolLogo: String): Bitmap? {
    val contentResolver: ContentResolver = context.contentResolver
    var bitmap: Bitmap? = null

    if (schoolLogo == "") {
        // Load and resize drawable
        bitmap = resizeDrawableToBitmap(context, R.drawable.tutwuri_logo, 256)
    } else {
        try {
            bitmap = if (Build.VERSION.SDK_INT < 28) {
                MediaStore.Images.Media.getBitmap(contentResolver, Uri.parse(schoolLogo))
            } else {
                val source: ImageDecoder.Source = ImageDecoder.createSource(contentResolver, Uri.parse(schoolLogo))
                ImageDecoder.decodeBitmap(source)
            }

            // Resize the loaded bitmap to 256x256
            if (bitmap != null) {
                bitmap = Bitmap.createScaledBitmap(bitmap, 256, 256, true)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    return bitmap
}

fun resizeDrawableToBitmap(context: Context, drawableResId: Int, targetSize: Int): Bitmap {
    // Step 1: Load the Drawable image
    val drawable: Drawable = AppCompatResources.getDrawable(context, drawableResId)
        ?: throw IllegalArgumentException("Drawable not found")

    // Step 2: Convert the Drawable to a Bitmap
    val bitmap: Bitmap = drawable.toBitmap()

    // Step 3: Resize the Bitmap to the desired dimensions

    return Bitmap.createScaledBitmap(bitmap, targetSize, targetSize, false)
}


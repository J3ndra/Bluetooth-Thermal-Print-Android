package com.junianto.edcsekolah.util

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.ImageDecoder
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.ParcelFileDescriptor
import android.provider.MediaStore
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.graphics.drawable.toBitmap
import com.junianto.edcsekolah.R
import timber.log.Timber
import java.io.FileDescriptor
import java.io.IOException

fun loadAndResizeBitmap(context: Context, schoolLogo: String): Bitmap? {
    val contentResolver: ContentResolver = context.contentResolver
    var bitmap: Bitmap? = null

    if (schoolLogo == "") {
        // Load and resize drawable
        bitmap = resizeDrawableToBitmap(context, R.drawable.tut_wuri_logo_2, 256)
    } else {
        try {
            bitmap = ImageSaver(context)
                .setFileName("school_logo.png")
                .setDirectoryName("images")
                .load()

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

fun getBitmapFromUri(context: Context, uri: Uri): Bitmap? {
    try {
        val parcelFileDescriptor: ParcelFileDescriptor? = context.contentResolver.openFileDescriptor(uri, "r")
        val fileDescriptor: FileDescriptor? = parcelFileDescriptor?.fileDescriptor
        val image: Bitmap? = BitmapFactory.decodeFileDescriptor(fileDescriptor)
        parcelFileDescriptor?.close()
        return image
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return null
}


fun toGrayscale(srcImage: Bitmap): Bitmap {
    val bmpGrayscale = Bitmap.createBitmap(srcImage.width, srcImage.height, Bitmap.Config.ARGB_8888)

    val canvas = Canvas(bmpGrayscale)
    val paint = Paint()

    val cm = ColorMatrix()
    cm.setSaturation(0f)
    paint.colorFilter = ColorMatrixColorFilter(cm)
    canvas.drawBitmap(srcImage, 0f, 0f, paint)

    return bmpGrayscale
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


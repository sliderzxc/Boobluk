package com.test.boobluk.utils.image

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import com.test.boobluk.databinding.FragmentEditProfileBinding
import com.test.boobluk.utils.int.checkIfNumberIsInRange
import com.test.boobluk.utils.int.differenceBetweenTwoNumbers
import java.net.URI

class ImageHelper {

    fun onClickChooseImage(
        launcher: ActivityResultLauncher<Intent>,
    ) {
        val intentChooseImage = Intent()
        intentChooseImage.type = "image/*"
        intentChooseImage.action = Intent.ACTION_GET_CONTENT

        launcher.launch(intentChooseImage)
    }

    fun checkIfImageIsSquare(
        uri: Uri,
        context: Context,
        binding: FragmentEditProfileBinding
    ) {
        val contentResolver = context.contentResolver
        val inputStream = contentResolver.openInputStream(uri)
        val drawable = Drawable.createFromStream(inputStream, "")
        val height = drawable?.intrinsicHeight
        val width = drawable?.intrinsicWidth

        if (height == null || width == null) return

        val difference = differenceBetweenTwoNumbers(height, width)
        val differenceRange = (difference - 30..difference + 30)
        val resultData = checkIfNumberIsInRange(differenceRange, difference)

        if (resultData) binding.ivUserAvatar.setImageURI(uri)
    }

}
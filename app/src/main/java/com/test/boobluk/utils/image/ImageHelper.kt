package com.test.boobluk.utils.image

import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher

class ImageHelper {

    fun onClickChooseImage(
        launcher: ActivityResultLauncher<Intent>,
    ) {
        val intentChooseImage = Intent()
        intentChooseImage.type = "image/*"
        intentChooseImage.action = Intent.ACTION_GET_CONTENT

        launcher.launch(intentChooseImage)
    }

}
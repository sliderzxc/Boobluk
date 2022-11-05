package com.test.boobluk.utils.toast

import androidx.fragment.app.Fragment
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

fun showDarkMotionColorToast(fragment: Fragment, text: String) {
    MotionToast.darkColorToast(
        fragment.requireActivity(),
        null,
        text,
        MotionToastStyle.INFO,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION,
        null
    )
}
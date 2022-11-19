package com.test.boobluk.utils.toast

import androidx.fragment.app.Fragment
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

fun showDarkMotionInfoColorToast(fragment: Fragment, text: String) {
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

fun showDarkMotionSuccessColorToast(fragment: Fragment, text: String) {
    MotionToast.darkColorToast(
        fragment.requireActivity(),
        null,
        text,
        MotionToastStyle.SUCCESS,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION,
        null
    )
}


package com.test.boobluk.utils.preferences

import android.content.Context
import com.test.boobluk.utils.constants.Constants.SHARED_PREFERENCES_NOTIFICATION_ARRAY
import com.test.boobluk.utils.constants.Constants.SHARED_PREFERENCES_NOTIFICATION_PREFERENCES

fun Context.addNewItemToPreferencesNotificationArray(int: Int, username: String) {
    val prefs = this.getSharedPreferences(SHARED_PREFERENCES_NOTIFICATION_PREFERENCES+username, 0)
    val editor = prefs.edit()
    val array = this.getArrayFromPreferencesNotificationArray(username)
    editor.putInt(SHARED_PREFERENCES_NOTIFICATION_ARRAY, array.size+1)
    for (i in array.indices) editor.putInt(SHARED_PREFERENCES_NOTIFICATION_ARRAY + "_" + i, array[i].toString().toInt())
    editor.putInt(SHARED_PREFERENCES_NOTIFICATION_ARRAY + "_" + array.size, int)
    editor.apply()
}

fun Context.getArrayFromPreferencesNotificationArray(username: String): Array<Int?> {
    val prefs = this.getSharedPreferences(SHARED_PREFERENCES_NOTIFICATION_PREFERENCES + username, 0)
    val size = prefs.getInt(SHARED_PREFERENCES_NOTIFICATION_ARRAY, 0)
    val array = arrayOfNulls<Int>(size)
    for (i in 0 until size) array[i] = prefs.getInt(SHARED_PREFERENCES_NOTIFICATION_ARRAY + "_" + i, 0)
    return array
}

fun Context.clearPreferencesNotificationArray(username: String) {
    val prefs = this.getSharedPreferences(SHARED_PREFERENCES_NOTIFICATION_PREFERENCES + username, 0)
    val editor = prefs.edit()
    editor.clear()
    editor.apply()
}

fun generateRandomNumber(): Int {
    return (Int.MIN_VALUE..Int.MAX_VALUE).random()
}
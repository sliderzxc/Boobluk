package com.test.boobluk.network.notification

import android.app.NotificationManager
import android.content.Context
import com.application.isradeleon.notify.Notify
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.test.boobluk.R
import com.test.boobluk.data.entities.UserInfo
import com.test.boobluk.utils.constants.Constants
import com.test.boobluk.utils.constants.Constants.REFERENCE_INIT_REALTIME_DATABASE
import com.test.boobluk.utils.constants.Constants.REFERENCE_USER_INFO
import com.test.boobluk.utils.preferences.addNewItemToPreferencesNotificationArray
import com.test.boobluk.utils.preferences.clearPreferencesNotificationArray
import com.test.boobluk.utils.preferences.generateRandomNumber

fun showNotification(
    context: Context,
    titleAndUsername: String,
    content: String,
) {
    val number = generateRandomNumber()
    context.addNewItemToPreferencesNotificationArray(number, titleAndUsername)
    Notify.build(context)
        .setTitle(titleAndUsername)
        .setContent(content)
        .setImportance(Notify.NotifyImportance.HIGH)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setId(number)
        .show()
}

fun hideNotifications(
    context: Context,
    notifyIdArray: Array<Int?>,
    username: String
) {
    val notificationService = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notifyIdArray.forEach { notifyId ->
        notifyId?.let { notificationService.cancel(it) }
    }
    context.clearPreferencesNotificationArray(username)
}
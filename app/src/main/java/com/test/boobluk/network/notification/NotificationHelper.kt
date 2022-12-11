package com.test.boobluk.network.notification

import android.app.NotificationManager
import android.content.Context
import com.application.isradeleon.notify.Notify
import com.test.boobluk.R
import com.test.boobluk.utils.preferences.addNewItemToPreferencesNotificationArray
import com.test.boobluk.utils.preferences.clearPreferencesNotificationArray
import com.test.boobluk.utils.preferences.generateRandomNumber

fun showNotification(
    context: Context,
    titleAndUsername: String,
    content: String
) {
    val number = generateRandomNumber()
    context.addNewItemToPreferencesNotificationArray(number, titleAndUsername)
    Notify.build(context)
        .setTitle(titleAndUsername)
        .setContent(content)
        .setImportance(Notify.NotifyImportance.HIGH)
        .setId(number)
        .setSmallIcon(R.drawable.app_icon)
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
package com.test.boobluk.data.entities.notification

import com.test.boobluk.data.entities.notification.NotificationData

data class PushNotification(
    val to: String,
    val notification: NotificationData
)
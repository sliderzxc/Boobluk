package com.test.boobluk.network.repository

import com.test.boobluk.data.entities.PushNotification
import com.test.boobluk.network.api.NotificationApi
import retrofit2.Response

class NotificationRepository(
    val notificationApi: NotificationApi
) {

    suspend fun sendNotification(pushNotification: PushNotification) : Response<PushNotification> {
        return notificationApi.sendNotification(pushNotification)
    }

}
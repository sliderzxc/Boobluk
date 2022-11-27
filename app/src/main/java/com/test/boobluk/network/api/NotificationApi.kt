package com.test.boobluk.network.api

import com.test.boobluk.data.entities.PushNotification
import com.test.boobluk.utils.constants.Constants.AUTHORIZATION_KEY
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificationApi {

    @Headers("Authorization: $AUTHORIZATION_KEY")
    @POST("fcm/send")
    suspend fun sendNotification(
        @Body pushNotification: PushNotification
    ) : Response<PushNotification>

}
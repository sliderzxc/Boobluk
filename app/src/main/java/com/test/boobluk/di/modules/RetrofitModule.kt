package com.test.boobluk.di.modules

import com.test.boobluk.network.api.NotificationApi
import com.test.boobluk.network.repository.NotificationRepository
import com.test.boobluk.utils.constants.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitModule {

    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideNotificationApi(
        retrofit: Retrofit
    ) : NotificationApi {
        return retrofit.create(NotificationApi::class.java)
    }

    @Provides
    fun provideNotificationRepository(
        notificationApi: NotificationApi
    ) : NotificationRepository {
        return NotificationRepository(
            notificationApi = notificationApi
        )
    }

}
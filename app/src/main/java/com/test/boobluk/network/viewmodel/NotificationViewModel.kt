package com.test.boobluk.network.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.boobluk.data.entities.PushNotification
import com.test.boobluk.network.repository.NotificationRepository
import kotlinx.coroutines.launch

class NotificationViewModel(
    val notificationRepository: NotificationRepository
) : ViewModel() {

    fun sendNotification(pushNotification: PushNotification) {
        viewModelScope.launch { notificationRepository.sendNotification(pushNotification) }
    }

}
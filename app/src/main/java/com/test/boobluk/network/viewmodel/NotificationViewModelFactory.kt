package com.test.boobluk.network.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.boobluk.network.repository.NotificationRepository

class NotificationViewModelFactory(
    val notificationRepository: NotificationRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotificationViewModel(
            notificationRepository = notificationRepository
        ) as T
    }
}
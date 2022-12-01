package com.test.boobluk.data.entities

data class LastMessage(
    val message: String? = null,
    val sentMessage: Boolean? = null,
    val receivedMessage: Boolean? = null,
    val sharedDataForSort: Long = 0,
)

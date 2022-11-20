package com.test.boobluk.data.entities

data class Message(
    val message: String? = null,
    val data: String? = null,
    val sharedData: String? = null,
    val sharedDataForSort: String? = null,
    var isSentMessage: Boolean? = null,
    var isReceivedMessage: Boolean? = null
)
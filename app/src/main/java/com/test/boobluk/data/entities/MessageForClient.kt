package com.test.boobluk.data.entities

data class MessageForClient(
    val message: String? = null,
    val data: String? = null,
    val sharedData: String? = null,
    var isSentMessage: Boolean? = null,
    var isReceivedMessage: Boolean? = null
)
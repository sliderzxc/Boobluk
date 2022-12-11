package com.test.boobluk.data.entities.chat

import com.test.boobluk.data.entities.messages.LastMessage

data class AboutChat(
    val username: String? = null,
    var lastMessage: LastMessage? = null,
    val avatar: String? = null,
    val uid: String? = null
)

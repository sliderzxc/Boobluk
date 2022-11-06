package com.test.boobluk.data.entities

data class User(
    val email: String,
    var isEmailConfirmed: Boolean,
    val uid: String,
    val username: String
)

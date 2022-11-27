package com.test.boobluk.data.entities

import com.test.boobluk.utils.gender.Gender

data class UserInfo(
    val email: String? = null,
    var isEmailConfirmed: Boolean? = null,
    val uid: String? = null,
    val username: String? = null,
    val avatar: String? = null,
    val gender: Gender? = null,
    var bio: String? = null,
    var token: String? = null
)
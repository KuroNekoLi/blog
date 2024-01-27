package com.example.blogmultiplatform2.models

import kotlinx.serialization.*

@Serializable
actual data class User(
    @SerialName(value = "_id")
//    actual val _id: String = ObjectIdGenerator.newObjectId<String>().id.toHexString(),
    actual val _id: String = "",
    actual val username: String = "",
    actual val password: String = ""
)

@Serializable
actual data class UserWithoutPassword(
    @SerialName(value = "_id")
    actual val _id: String = "",
    actual val username: String = "",
)


package com.example.blogmultiplatform2.util

import com.example.blogmultiplatform2.models.*
import com.varabyte.kobweb.browser.*
import kotlinx.browser.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*

suspend fun checkUserExistence(user: User): UserWithoutPassword? {
    return try {
        val result = window.api.tryPost(
            apiPath = "usercheck",
            body = Json.encodeToString(user).encodeToByteArray()
        )
        result?.decodeToString()?.let { Json.decodeFromString<UserWithoutPassword>(it) }
    } catch (e: Exception) {
        println(e.message)
        null
    }
}

suspend fun checkUserId(id: String): Boolean {
    return try {
        val result = window.api.tryPost(
            apiPath = "checkuserid",
            body = Json.encodeToString(id).encodeToByteArray()
        )

        Json.decodeFromString(result.toString())
    } catch (e: Exception) {
        println(e.message.toString())
        false
    }
}
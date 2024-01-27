package com.example.blogmultiplatform2.util

import com.example.blogmultiplatform2.models.User
import com.example.blogmultiplatform2.models.UserWithoutPassword
import com.varabyte.kobweb.browser.api
import kotlinx.browser.window
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

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
suspend fun checkUserId(id:String):Boolean{
    return try {
        val result = window.api.tryPost(
            apiPath = "checkuserid",
            body = Json.encodeToString(id).encodeToByteArray()
        )

        Json.decodeFromString(result.toString())
    }catch (e:Exception){
        println(e.message.toString())
        false
    }
}
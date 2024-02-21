package com.example.blogmultiplatform2.util

import com.example.blogmultiplatform2.models.*
import com.varabyte.kobweb.browser.*
import com.varabyte.kobweb.browser.http.*
import kotlinx.browser.*
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import org.w3c.dom.*
import kotlin.js.*

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

suspend fun fetChRandomJoke(onComplete: (RandomJoke) -> Unit) {
    val date = localStorage["date"]
    if (date != null) {
        val difference = (Date.now() - date.toDouble())
        val dayHasPassed = difference >= 86400000 //一天的毫秒
        if (dayHasPassed) {
            try {
                val result = window.http.get(Constants.HUMOR_API_URL).decodeToString()
                onComplete(Json.decodeFromString(result)) //string -> RandomJoke
                localStorage["date"] = Date.now().toString()
                localStorage["joke"] = result
            } catch (e: Exception) {
                onComplete(RandomJoke(-1, e.message.toString()))
                println(e.message)
            }
        } else {
            try {
                localStorage["joke"]?.let { onComplete(Json.decodeFromString<RandomJoke>(it)) }
            } catch (e: Exception) {
                onComplete(RandomJoke(-1, e.message.toString()))
                println(e.message)
            }

        }
    } else {
        try {
            val result = window.http.get(Constants.HUMOR_API_URL).decodeToString()
            onComplete(Json.decodeFromString(result))
            localStorage["date"] = Date.now().toString()
            localStorage["joke"] = result
        } catch (e: Exception) {
            onComplete(RandomJoke(-1, e.message.toString()))
            println(e.message)
        }

    }
}
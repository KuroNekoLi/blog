package com.example.blogmultiplatform2.data

import com.example.blogmultiplatform2.models.*

interface MongoRepository {
    suspend fun checkUserExistence(user: User): User?
    suspend fun checkUserId(id: String): Boolean
}
package com.example.blogmultiplatform2.models

import kotlinx.serialization.*

@Serializable
data class RandomJoke(
    val id: Int,
    val joke: String
)

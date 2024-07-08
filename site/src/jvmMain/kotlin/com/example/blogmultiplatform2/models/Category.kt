package com.example.blogmultiplatform2.models

import kotlinx.serialization.Serializable

@Serializable
actual enum class Category(val color: String) {
    Technology(color = Theme.Green.hex),
    Programming(Theme.Yellow.hex),
    Design(Theme.Purple.hex)
}
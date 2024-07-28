package com.example.blogmultiplatform2.models

import com.example.blogmultiplatform2.util.*

enum class EditorKey(
    val icon: String,
) {
    Bold(icon = Res.Icon.bold),
    Italic(icon = Res.Icon.italic),
    Link(icon = Res.Icon.link),
    Title(icon = Res.Icon.title),
    Subtitle(icon = Res.Icon.subtitle),
    Quote(icon = Res.Icon.quote),
    Code(icon = Res.Icon.code),
    Image(icon = Res.Icon.image),
//    Checkmark(icon = Res.Icon.checkmark)
}
package com.example.blogmultiplatform2.components

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.*
import com.varabyte.kobweb.compose.ui.*
import com.varabyte.kobweb.compose.ui.modifiers.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .height(100.vh)
            .padding(topBottom = 50.px),
        contentAlignment = Alignment.Center
    ) {
        Div(
            attrs = Modifier
                .classNames("spinner-border", "text-primary")
                .toAttrs()
        ) {
            Span(
                attrs = Modifier
                    .classNames("visually-hidden")
                    .toAttrs()
            ) {
                Text("Loading...")
            }
        }
    }
}
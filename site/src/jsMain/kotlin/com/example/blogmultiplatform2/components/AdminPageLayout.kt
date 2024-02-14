package com.example.blogmultiplatform2.components

import androidx.compose.runtime.*
import com.example.blogmultiplatform2.util.*
import com.varabyte.kobweb.compose.foundation.layout.*
import com.varabyte.kobweb.compose.ui.*
import com.varabyte.kobweb.compose.ui.modifiers.*
import org.jetbrains.compose.web.css.*

@Composable
fun AdminPageLayout(content: @Composable () -> Unit) {
    var overflowMenuOpened by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .maxWidth(Constants.PAGE_WIDTH.px)
        ) {
            SidePanel { overflowMenuOpened = true }
            if (overflowMenuOpened) OverflowSidePanel { overflowMenuOpened = false }
        }
    }
}
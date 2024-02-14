package com.example.blogmultiplatform2.pages.admin

import androidx.compose.runtime.*
import com.example.blogmultiplatform2.components.*
import com.example.blogmultiplatform2.util.*
import com.example.blogmultiplatform2.util.Constants.PAGE_WIDTH
import com.varabyte.kobweb.compose.foundation.layout.*
import com.varabyte.kobweb.compose.ui.*
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.*
import org.jetbrains.compose.web.css.*

@Page
@Composable
fun HomePage() {
    isUserLoggedIn {
        HomeScreen()
    }
}

@Composable
fun HomeScreen() {
    var overflowMenuOpened by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .maxWidth(PAGE_WIDTH.px)
        ) {
            SidePanel { overflowMenuOpened = true }
            if (overflowMenuOpened) OverflowSidePanel { overflowMenuOpened = false }
        }
    }
}
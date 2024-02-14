package com.example.blogmultiplatform2.pages.admin

import androidx.compose.runtime.*
import com.example.blogmultiplatform2.components.*
import com.example.blogmultiplatform2.util.*
import com.varabyte.kobweb.core.*

@Page
@Composable
fun HomePage() {
    isUserLoggedIn {
        HomeScreen()
    }
}

@Composable
fun HomeScreen() {
    AdminPageLayout {

    }
}
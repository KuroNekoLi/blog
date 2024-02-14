package com.example.blogmultiplatform2.util

import androidx.compose.runtime.*
import com.example.blogmultiplatform2.navigation.Screen
import com.varabyte.kobweb.core.*
import kotlinx.browser.*
import org.w3c.dom.*

@Composable
fun isUserLoggedIn(content: @Composable () -> Unit) {
    val context = rememberPageContext()
    val remembered = remember { localStorage["remember"].toBoolean() }
    val userId = remember { localStorage["userId"] }
    var userIdExists by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        userIdExists = if (!userId.isNullOrEmpty()) checkUserId(id = userId) else false
        userIdExists = true
        if (!(remembered && userIdExists)) {
            context.router.navigateTo(Screen.AdminLogin.route)
        }
    }

    if (remembered && userIdExists) {
        content()
    } else {
        println("loading....")
    }
}
fun logout(){
    localStorage["remember"] = "false"
    localStorage["userId"] = ""
    localStorage["username"] = ""
}
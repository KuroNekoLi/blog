package com.example.blogmultiplatform2.navigation

sealed class Screen(val route: String) {
    object AdminHome : Screen(route = "/admin/")
    object AdminLogin : Screen(route = "/admin/login")
    data object AdminCreate : Screen(route = "/admin/create")
    data object AdminMyPosts : Screen(route = "/admin/myposts")
}
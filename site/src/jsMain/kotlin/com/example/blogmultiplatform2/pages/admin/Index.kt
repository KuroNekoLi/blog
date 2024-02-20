package com.example.blogmultiplatform2.pages.admin

import androidx.compose.runtime.*
import com.example.blogmultiplatform2.components.*
import com.example.blogmultiplatform2.models.*
import com.example.blogmultiplatform2.navigation.Screen
import com.example.blogmultiplatform2.util.*
import com.example.blogmultiplatform2.util.Constants.FONT_FAMILY
import com.example.blogmultiplatform2.util.Constants.HUMOR_API_URL
import com.example.blogmultiplatform2.util.Constants.PAGE_WIDTH
import com.example.blogmultiplatform2.util.Constants.SIDE_PANEL_WIDTH
import com.varabyte.kobweb.browser.http.*
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.foundation.layout.*
import com.varabyte.kobweb.compose.ui.*
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.*
import com.varabyte.kobweb.silk.components.graphics.*
import com.varabyte.kobweb.silk.components.icons.fa.*
import com.varabyte.kobweb.silk.components.style.breakpoint.*
import com.varabyte.kobweb.silk.components.text.*
import com.varabyte.kobweb.silk.theme.breakpoint.*
import kotlinx.browser.*
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import org.jetbrains.compose.web.css.*
import org.w3c.dom.*
import kotlin.js.*

@Page
@Composable
fun HomePage() {
    isUserLoggedIn {
        HomeScreen()
    }
}

@Composable
fun HomeScreen() {
    val scope = rememberCoroutineScope()
    var randomJoke by remember { mutableStateOf<RandomJoke?>(null) }
    LaunchedEffect(Unit) {
        val date = localStorage["date"]
        if (date != null) {
            val difference = (Date.now() - date.toDouble())
            val dayHasPassed = difference >= 86400000 //一天的毫秒
            if (dayHasPassed) {
                scope.launch {
                    try {
                        val result = window.http.get(HUMOR_API_URL).decodeToString()
                        randomJoke =
                            Json.decodeFromString<RandomJoke>(result) //string -> RandomJoke
                        localStorage["date"] = Date.now().toString()
                        localStorage["joke"] = result
                    } catch (e: Exception) {
                        println(e.message)
                    }
                }
            } else {
                try {
                    randomJoke = localStorage["joke"]?.let { Json.decodeFromString<RandomJoke>(it) }
                } catch (e: Exception) {
                    randomJoke = RandomJoke(-1, "Unexpected Error.")
                    println(e.message)
                }

            }
        } else {
            scope.launch {
                try {
                    val result = window.http.get(HUMOR_API_URL).decodeToString()
                    randomJoke = Json.decodeFromString<RandomJoke>(result) //string -> RandomJoke
                    localStorage["date"] = Date.now().toString()
                    localStorage["joke"] = result
                } catch (e: Exception) {
                    println(e.message)
                }
            }
        }
    }
    AdminPageLayout {
//        HomeContent(randomJoke = RandomJoke(2, "some random joke...:some random joke...:some random joke..."))
        HomeContent(randomJoke = randomJoke)
        AddButton()
    }
}

@Composable
fun HomeContent(randomJoke: RandomJoke?) {
    val breakpoint = rememberBreakpoint()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(left = if (breakpoint > Breakpoint.MD) SIDE_PANEL_WIDTH.px else 0.px),
        contentAlignment = Alignment.Center
    ) {
        if (randomJoke == null) {
            println("loading a joke...")
        }
        randomJoke?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(topBottom = 50.px),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (randomJoke.id != -1) {
                    Image(
                        modifier = Modifier
                            .size(150.px)
                            .margin(bottom = 50.px),
                        src = Res.Image.laugh,
                        description = "laugh image"
                    )
                }
                if (randomJoke.joke.contains("Q")) {
                    SpanText(
                        modifier = Modifier
                            .margin(bottom = 14.px)
                            .fillMaxWidth(40.percent)
                            .textAlign(TextAlign.Center)
                            .color(Theme.Secondary.rgb)
                            .fontSize(28.px)
                            .fontFamily(FONT_FAMILY)
                            .fontWeight(FontWeight.Bold),
                        text = randomJoke.joke.split(":")[1].dropLast(1) //把A拿掉
                    )
                    SpanText(
                        modifier = Modifier
                            .fillMaxWidth(40.percent)
                            .textAlign(TextAlign.Center)
                            .color(Theme.HalfBlack.rgb)
                            .fontSize(20.px)
                            .fontFamily(FONT_FAMILY)
                            .fontWeight(FontWeight.Normal),
                        text = randomJoke.joke.split(":").last()
                    )
                } else {
                    SpanText(
                        modifier = Modifier
                            .margin(bottom = 14.px)
                            .fillMaxWidth(40.percent)
                            .textAlign(TextAlign.Center)
                            .color(Theme.Secondary.rgb)
                            .fontSize(28.px)
                            .fontFamily(FONT_FAMILY)
                            .fontWeight(FontWeight.Bold),
                        text = randomJoke.joke
                    )
                }
            }
        }
    }
}

/**
 * Add button
 *"none"：將pointer-events設定為"none"意味著該元素永遠不會成為指標事件的目標。換句話說，任何的滑鼠點擊、懸停等操作會表現得好像這個設定了此屬性的元素根本不存在一樣。這些事件會“穿透”該元素，被下方的任何元素捕獲。這在你想要覆蓋一個元素作為視覺目的但不希望它干預用戶與其後面元素的互動時非常有用。
 *
 * "auto"：這會將pointer-events的行為重置為預設值，這意味著該元素可以再次成為指標事件的目標。這在內層Box上使用，以確保即便其父元素設定了pointer-events為"none"，它仍然可以被互動（例如，被點擊）。
 */
@Composable
fun AddButton() {
    val breakpoint = rememberBreakpoint()
    val context = rememberPageContext()
    Box(
        modifier = Modifier
            .height(100.vh)
            .fillMaxWidth()
            .maxWidth(PAGE_WIDTH.px)
            .position(Position.Fixed)
            .styleModifier {
                property(
                    "pointer-events",
                    "none"
                )
            }, //basically this box which fills the whole screen will pass all those click events and the pointers down to its component or a child component.
        contentAlignment = Alignment.BottomEnd
    ) {
        Box(modifier = Modifier
            .margin(
                right = if (breakpoint > Breakpoint.MD) 40.px else 20.px,
                bottom = if (breakpoint > Breakpoint.MD) 40.px else 20.px
            )
            .backgroundColor(Theme.Primary.rgb)
            .size(if (breakpoint > Breakpoint.MD) 80.px else 50.px)
            .borderRadius(14.px)
            .cursor(Cursor.Pointer)
            .onClick {
                context.router.navigateTo(Screen.AdminCreate.route)
            }
            .styleModifier { property("pointer-events", "auto") },
            contentAlignment = Alignment.Center
        ) {
            FaPlus(
                modifier = Modifier.color(Color.white),
                size = IconSize.LG
            )
        }
    }
}
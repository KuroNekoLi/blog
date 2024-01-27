package com.example.blogmultiplatform2.components

import androidx.compose.runtime.*
import com.example.blogmultiplatform2.models.*
import com.example.blogmultiplatform2.util.*
import com.example.blogmultiplatform2.util.Constants.FONT_FAMILY
import com.example.blogmultiplatform2.util.Constants.SIDE_PANEL_WIDTH
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.dom.svg.*
import com.varabyte.kobweb.compose.foundation.layout.*
import com.varabyte.kobweb.compose.ui.*
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.graphics.*
import com.varabyte.kobweb.silk.components.text.*
import org.jetbrains.compose.web.css.*

@Composable
fun SidePanel() {
    Column(
        modifier = Modifier
            .padding(leftRight = 40.px, topBottom = 50.px)
            .width(SIDE_PANEL_WIDTH.px)
            .height(100.vh)
            .position(Position.Fixed)
            .backgroundColor(Theme.Secondary.rgb)
            .zIndex(9)
    ) {
        Image(
            modifier = Modifier.margin(bottom = 60.px),
            src = Res.Image.logo,
        )
        SpanText(
            modifier = Modifier
                .margin(bottom = 30.px)
                .fontFamily(FONT_FAMILY)
                .fontSize(14.px)
                .color(Theme.Halfwhite.rgb),
            text = "Dashboard"
        )
        NavigationItem(
            modifier = Modifier.margin(bottom = 24.px),
            title = "Home",
            selected = true,
            icon = Res.PathIcon.home,
            onClick = {}
        )
        NavigationItem(
            modifier = Modifier.margin(bottom = 24.px),
            title = "Create",
            icon = Res.PathIcon.create,
            onClick = {}
        )
        NavigationItem(
            modifier = Modifier.margin(bottom = 24.px),
            title = "Posts",
            icon = Res.PathIcon.posts,
            onClick = {}
        )
        NavigationItem(
            title = "Logout",
            icon = Res.PathIcon.logout,
            onClick = {}
        )
    }
}

@Composable
fun NavigationItem(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    title: String,
    icon: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .cursor(Cursor.Pointer)
            .onClick { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        VectorIcon(
            modifier = Modifier.margin(right = 10.px),
            pathData = icon,
            color = if (selected) Theme.Primary.hex else Theme.White.hex
        )
        SpanText(
            modifier = Modifier
                .fontFamily(FONT_FAMILY)
                .fontSize(16.px)
                .color(if (selected) Theme.Primary.rgb else Theme.White.rgb),
            text = title
        )
    }
}

@Composable
fun VectorIcon(
    modifier: Modifier,
    pathData: String,
    color: String
) {
    Svg(
        attrs = modifier
            .width(24.px)
            .height(24.px)
            .toAttrs {
                attr("viewBox", "0 0 24 24")
                attr("fill", "none")
            }
    ) {
        Path(
            attrs = Modifier
                .toAttrs {
                    attr("d", pathData)
                    attr("stroke", color)
                    attr("stroke-width", "2")
                    attr("stroke-linecap", "2")
                    attr("stroke-linejoin", "round")
                }
        )
    }
}
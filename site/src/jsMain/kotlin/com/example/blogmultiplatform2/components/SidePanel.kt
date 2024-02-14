package com.example.blogmultiplatform2.components

import androidx.compose.runtime.*
import com.example.blogmultiplatform2.models.*
import com.example.blogmultiplatform2.navigation.*
import com.example.blogmultiplatform2.styles.*
import com.example.blogmultiplatform2.util.*
import com.example.blogmultiplatform2.util.Constants.COLLAPSED_PANEL_HEIGHT
import com.example.blogmultiplatform2.util.Constants.FONT_FAMILY
import com.example.blogmultiplatform2.util.Constants.SIDE_PANEL_WIDTH
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.dom.svg.*
import com.varabyte.kobweb.compose.foundation.layout.*
import com.varabyte.kobweb.compose.ui.*
import com.varabyte.kobweb.compose.ui.graphics.*
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.*
import com.varabyte.kobweb.silk.components.graphics.*
import com.varabyte.kobweb.silk.components.icons.fa.*
import com.varabyte.kobweb.silk.components.style.*
import com.varabyte.kobweb.silk.components.style.breakpoint.*
import com.varabyte.kobweb.silk.components.text.*
import com.varabyte.kobweb.silk.theme.breakpoint.*
import org.jetbrains.compose.web.css.*

@Composable
fun SidePanel(onMenuClick: () -> Unit) {
    val breakpoint = rememberBreakpoint()
    if (breakpoint > Breakpoint.MD) {
        SidePanelInternal()
    } else {
        CollapseSidePanel { onMenuClick() }
    }
}

@Composable
fun SidePanelInternal() {
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
        NavigationItems()
    }
}

@Composable
private fun NavigationItems() {
    val context = rememberPageContext()
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
        selected = context.route.path == Screen.AdminHome.route,
        icon = Res.PathIcon.home,
        onClick = {
            context.router.navigateTo(Screen.AdminHome.route)
        }
    )
    NavigationItem(
        modifier = Modifier.margin(bottom = 24.px),
        title = "Create",
        selected = context.route.path == Screen.AdminCreate.route,
        icon = Res.PathIcon.create,
        onClick = {
            context.router.navigateTo(Screen.AdminCreate.route)
        }
    )
    NavigationItem(
        modifier = Modifier.margin(bottom = 24.px),
        title = "My Posts",
        selected = context.route.path == Screen.AdminMyPosts.route,
        icon = Res.PathIcon.posts,
        onClick = {
            context.router.navigateTo(Screen.AdminMyPosts.route)
        }
    )
    NavigationItem(
        title = "Logout",
        icon = Res.PathIcon.logout,
        onClick = {
            logout()
            context.router.navigateTo(Screen.AdminLogin.route)
        }
    )
}

@Composable
private fun NavigationItem(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    title: String,
    icon: String,
    onClick: () -> Unit
) {
    Row(
        modifier = NavigationItemStyle.toModifier()
            .then(modifier)
            .cursor(Cursor.Pointer)
            .onClick { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        VectorIcon(
            modifier = Modifier.margin(right = 10.px),
            selected = selected,
            pathData = icon,
        )
        SpanText(
            modifier = Modifier
                .id(Id.navigationText)
                .fontFamily(FONT_FAMILY)
                .fontSize(16.px)
                .thenIf(
                    condition = selected,
                    other = Modifier.color(Theme.Primary.rgb)
                ),
            text = title
        )
    }
}

@Composable
private fun VectorIcon(
    modifier: Modifier,
    selected: Boolean,
    pathData: String
) {
    Svg(
        attrs = modifier
            .id(Id.svgPatent)
            .width(24.px)
            .height(24.px)
            .toAttrs {
                attr("viewBox", "0 0 24 24")
                attr("fill", "none")
            }
    ) {
        Path(
            attrs = Modifier
                .id(Id.vectorIcon)
                .thenIf(
                    condition = selected,
                    other = Modifier.styleModifier {
                        property("stroke", Theme.Primary.hex)
                    }
                )
                .toAttrs {
                    attr("d", pathData)
                    attr("stroke-width", "2")
                    attr("stroke-linecap", "2")
                    attr("stroke-linejoin", "round")
                }
        )
    }
}

@Composable
private fun CollapseSidePanel(onMenuClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(COLLAPSED_PANEL_HEIGHT.px)
            .padding(leftRight = 24.px)
            .backgroundColor(Theme.Secondary.rgb),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FaBars(
            modifier = Modifier
                .margin(right = 24.px)
                .color(Colors.White)
                .cursor(Cursor.Pointer)
                .onClick { onMenuClick() },
            size = IconSize.XL
        )
        Image(
            modifier = Modifier.width(80.px),
            src = Res.Image.logo,
            description = "Logo Image"
        )
    }
}

@Composable
fun OverflowSidePanel(onMenuClose: () -> Unit) {
    val breakpoint = rememberBreakpoint()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.vh)
            .position(Position.Fixed)
            .zIndex(9)
            .backgroundColor(Theme.HalfBlack.rgb)
    ) {
        Column(
            modifier = Modifier
                .padding(24.px)
                .fillMaxHeight()
                .width(if (breakpoint < Breakpoint.MD) 50.percent else 25.percent)
                .backgroundColor(Theme.Secondary.rgb)
        ) {
            Row(
                modifier = Modifier.margin(bottom = 60.px),
                verticalAlignment = Alignment.CenterVertically
            ) {
                FaXmark(
                    modifier = Modifier
                        .margin(right = 20.px)
                        .color(Colors.White)
                        .cursor(Cursor.Pointer)
                        .onClick { onMenuClose() },
                    size = IconSize.LG
                )
                Image(
                    modifier = Modifier.width(80.px),
                    src = Res.Image.logo,
                    description = "Logo Image"
                )
            }
            NavigationItems()
        }
    }
}
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
import kotlinx.coroutines.*
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

/**
 * Overflow side panel
 *
 * @param onMenuClose
 * .overflow(Overflow.Auto)修飾器用於控制元素內容的溢出行為。Overflow是一個CSS屬性，它決定了當元素的內容超出其指定大小時應如何處理。Overflow.Auto的含義是，僅當內容超出元素容器的大小時，才會顯示滾動條，允許用戶滾動查看隱藏的內容。
 *
 * 在CSS中，overflow屬性可以設置為以下值之一：
 *
 * visible：溢出的內容不會被裁剪，會呈現在元素框之外。
 * hidden：溢出的內容會被裁剪，且不可見。
 * scroll：元素會永遠顯示滾動條，無論內容是否溢出。
 * auto：瀏覽器會根據內容是否溢出自動決定是否顯示滾動條。
 *
 * .scrollBehavior(ScrollBehavior.Smooth)
 * .scrollBehavior(ScrollBehavior.Smooth)修飾器用於定義滾動行為。ScrollBehavior是一個CSS屬性，它允許您指定滾動發生時的動畫效果。ScrollBehavior.Smooth意味著當用戶滾動時，滾動動作會平滑進行，而不是瞬間跳轉到新的位置。
 *
 * CSS scroll-behavior屬性支持以下值：
 *
 * auto：滾動發生時不使用平滑滾動效果，這是默認行為。
 * smooth：啟用平滑滾動效果，提供更舒適的滾動體驗。
 */
@Composable
fun OverflowSidePanel(onMenuClose: () -> Unit) {
    val breakpoint = rememberBreakpoint()
    val scope = rememberCoroutineScope()
    var translateX by remember { mutableStateOf((-100).percent) }
    var opacity by remember { mutableStateOf((0).percent) }

    fun closePanel() {
        scope.launch {
            translateX = (-100).percent
            opacity = 0.percent
            delay(500)
            onMenuClose()
        }

    }

    LaunchedEffect(breakpoint) {
        translateX = 0.percent
        opacity = 100.percent
        if (breakpoint > Breakpoint.MD) {
            closePanel()
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.vh)
            .position(Position.Fixed)
            .zIndex(9)
            .opacity(opacity)
            .transition(CSSTransition(property = "opacity", duration = 300.ms))
            .backgroundColor(Theme.HalfBlack.rgb)
    ) {
        Column(
            modifier = Modifier
                .padding(24.px)
                .fillMaxHeight()
                .width(if (breakpoint < Breakpoint.MD) 50.percent else 25.percent)
                .translateX(translateX)
                .transition(CSSTransition(property = "translate", duration = 300.ms))
                .overflow(Overflow.Auto)
                .scrollBehavior(ScrollBehavior.Smooth)
                .backgroundColor(Theme.Secondary.rgb)
        ) {
            Row(
                modifier = Modifier.margin(bottom = 60.px, top = 24.px),
                verticalAlignment = Alignment.CenterVertically
            ) {
                FaXmark(
                    modifier = Modifier
                        .margin(right = 20.px)
                        .color(Colors.White)
                        .cursor(Cursor.Pointer)
                        .onClick { closePanel() },
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
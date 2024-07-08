package com.example.blogmultiplatform2.pages.admin

import androidx.compose.runtime.*
import com.example.blogmultiplatform2.components.*
import com.example.blogmultiplatform2.models.*
import com.example.blogmultiplatform2.util.*
import com.example.blogmultiplatform2.util.Constants.FONT_FAMILY
import com.example.blogmultiplatform2.util.Constants.SIDE_PANEL_WIDTH
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.foundation.layout.*
import com.varabyte.kobweb.compose.ui.*
import com.varabyte.kobweb.compose.ui.graphics.*
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.*
import com.varabyte.kobweb.silk.components.forms.*
import com.varabyte.kobweb.silk.components.layout.*
import com.varabyte.kobweb.silk.components.style.breakpoint.*
import com.varabyte.kobweb.silk.components.text.*
import com.varabyte.kobweb.silk.theme.breakpoint.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

@Page
@Composable
fun CreatePage() {
    isUserLoggedIn {
        CreateScreen()
    }
}

@Composable
fun CreateScreen() {
    val breakpoint = rememberBreakpoint()
    var popularSwitch by remember { mutableStateOf(false) }
    var mainSwitch by remember { mutableStateOf(false) }
    var sponsoredSwitch by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf(Category.Programming) }
    AdminPageLayout {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .margin(topBottom = 50.px)
                .padding(left = if (breakpoint > Breakpoint.MD) SIDE_PANEL_WIDTH.px else 0.px),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .maxWidth(700.px),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SimpleGrid(numColumns = numColumns(base = 1, sm = 3)) {
                    Row(
                        modifier = Modifier
                            .margin(
                                right = if (breakpoint > Breakpoint.SM) 0.px else 24.px,
                                left = if (breakpoint > Breakpoint.SM) 12.px else 0.px
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Switch(
                            modifier = Modifier.margin(right = 8.px),
                            checked = popularSwitch,
                            onCheckedChange = { popularSwitch = it },
                            size = SwitchSize.LG
                        )
                        SpanText(
                            modifier = Modifier
                                .fontSize(14.px)
                                .fontFamily(FONT_FAMILY)
                                .color(Theme.HalfBlack.rgb),
                            text = "Popular"
                        )
                    }
                    Row(
                        modifier = Modifier
                            .margin(
                                right = if (breakpoint > Breakpoint.SM) 0.px else 24.px,
                                left = if (breakpoint > Breakpoint.SM) 12.px else 0.px
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Switch(
                            modifier = Modifier.margin(right = 8.px),
                            checked = mainSwitch,
                            onCheckedChange = { mainSwitch = it },
                            size = SwitchSize.LG
                        )
                        SpanText(
                            modifier = Modifier
                                .fontSize(14.px)
                                .fontFamily(FONT_FAMILY)
                                .color(Theme.HalfBlack.rgb),
                            text = "Main"
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Switch(
                            modifier = Modifier.margin(right = 8.px),
                            checked = sponsoredSwitch,
                            onCheckedChange = { sponsoredSwitch = it },
                            size = SwitchSize.LG
                        )
                        SpanText(
                            modifier = Modifier
                                .fontSize(14.px)
                                .fontFamily(FONT_FAMILY)
                                .color(Theme.HalfBlack.rgb),
                            text = "Sponsored"
                        )
                    }
                }
                Input(
                    type = InputType.Text,
                    attrs = Modifier
                        .fillMaxWidth()
                        .height(54.px)
                        .margin(topBottom = 12.px)
                        .padding(right = 20.px)
                        .backgroundColor(Theme.LightGray.rgb)
                        .borderRadius(r = 4.px)
                        .border(
                            width = 0.px,
                            style = LineStyle.None,
                            color = Colors.Transparent
                        )
                        .outline(
                            width = 0.px,
                            style = LineStyle.None,
                            color = Colors.Transparent
                        )
                        .fontFamily(FONT_FAMILY)
                        .fontSize(16.px)
                        .toAttrs {
                            attr("placeholder", "Title")
                        }
                )
                Input(
                    type = InputType.Text,
                    attrs = Modifier
                        .fillMaxWidth()
                        .height(54.px)
                        .margin(bottom = 12.px)
                        .padding(right = 20.px)
                        .backgroundColor(Theme.LightGray.rgb)
                        .borderRadius(r = 4.px)
                        .border(
                            width = 0.px,
                            style = LineStyle.None,
                            color = Colors.Transparent
                        )
                        .outline(
                            width = 0.px,
                            style = LineStyle.None,
                            color = Colors.Transparent
                        )
                        .fontFamily(FONT_FAMILY)
                        .fontSize(16.px)
                        .toAttrs {
                            attr("placeholder", "Subtitle")
                        }
                )
                CategoryDropdown(
                    selectedCategory = selectedCategory,
                    onCategorySelected = { selectedCategory = it }
                )

            }
        }
    }
}

/**
 * Category dropdown
 *
 * @param selectedCategory
 * @param onCategorySelected
 * @receiver
 *
 * 關於dropdown可參考 https://getbootstrap.com/docs/5.3/components/dropdowns/#overview
 */
@Composable
fun CategoryDropdown(
    selectedCategory: Category,
    onCategorySelected: (Category) -> Unit
) {
    Box(
        modifier = Modifier
            .margin(topBottom = 12.px)
            .classNames("dropdown") // Bootstrap class
            .fillMaxWidth()
            .height(54.px)
            .backgroundColor(Theme.LightGray.rgb)
            .cursor(Cursor.Pointer)
            .attrsModifier {
                attr("data-bs-toggle", "dropdown")
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(leftRight = 20.px),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SpanText(
                modifier = Modifier
                    .fillMaxWidth()
                    .fontSize(16.px)
                    .fontFamily(FONT_FAMILY),
                text = selectedCategory.name
            )
            Box(
                modifier = Modifier
                    .classNames("dropdown-toggle") // transform this into a dropdown arrow
            )
            /**
             * Ul 組件:
             *
             * 用於創建一個無序列表 (<ul> 元素)。
             * 使用 Modifier 來設置 CSS 樣式。
             * 應用了 Bootstrap 的 dropdown-menu 類別，使其具有下拉菜單的樣式。
             * fillMaxWidth() 讓列表寬度填滿可用空間。
             * Li 組件:
             *
             * 用於創建列表項 (<li> 元素)。
             * 包含在 Ul 組件中，用來表示下拉菜單的每一項。
             * A 組件:
             *
             * 用於創建鏈接 (<a> 元素)。
             * 使用 Modifier 來設置樣式。
             * 應用了 Bootstrap 的 dropdown-item 類別，使每個項目具有下拉菜單項的樣式。
             * 設置了文本顏色、字體和字體大小。
             * onClick 回調函數用於處理選擇分類的邏輯。
             */
            Ul(
                attrs = Modifier
                    .fillMaxWidth()
                    .classNames("dropdown-menu") // Bootstrap class
                    .toAttrs()
            ) {
                Category.values().forEach { category ->
                    Li {
                        A(
                            attrs = Modifier
                                .classNames("dropdown-item")
                                .color(Colors.Black)
                                .fontFamily(FONT_FAMILY)
                                .fontSize(16.px)
                                .onClick {
                                    onCategorySelected(category)
                                }
                                .toAttrs()
                        ) {
                            Text(category.name)
                        }
                    }
                }
            }
        }
    }
}
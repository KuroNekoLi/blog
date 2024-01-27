package com.example.blogmultiplatform2.styles

import com.example.blogmultiplatform2.models.*
import com.example.blogmultiplatform2.util.*
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.ui.*
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.style.*
import org.jetbrains.compose.web.css.*

val NavigationItemStyle by ComponentStyle {
    cssRule(" > #${Id.svgPatent} > #${Id.vectorIcon}") {
        Modifier
            .transition(CSSTransition(property = TransitionProperty.All, duration = 300.ms))
            .styleModifier {
                property("stroke", Theme.White.hex)
            }
    }
    cssRule(":hover > #svgParent > #vectorIcon") {
        Modifier
            .styleModifier {
                property("stroke", Theme.Primary.hex)
            }
    }
    cssRule(" > #${Id.navigationText}") {
        Modifier
            .transition(CSSTransition(property = TransitionProperty.All, duration = 300.ms))
            .color(Theme.White.rgb)
    }
    cssRule(":hover > #${Id.navigationText}") {
        Modifier.color(Theme.Primary.rgb)
    }
}
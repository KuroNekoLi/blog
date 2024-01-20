import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kobweb.application)
     alias(libs.plugins.kobwebx.markdown)
     alias(libs.plugins.serialization.plugin)
}

group = "com.example.blogmultiplatform2"
version = "1.0-SNAPSHOT"

kobweb {
    app {
        index {
            description.set("Powered by Kobweb")
        }
    }
}

kotlin {
    configAsKobwebApplication("blogmultiplatform2", includeServer = true)

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)
                implementation(libs.kobweb.core)
                implementation(libs.kobweb.silk)
                implementation(libs.silk.icons.fa)
                 implementation(libs.kobwebx.markdown)
            }
        }
        val jvmMain by getting {
            dependencies {
                compileOnly(libs.kobweb.api) // Provided by Kobweb backend at runtime
                implementation(libs.kobweb.api)
                implementation(libs.kmongo.database)
                implementation(libs.kotlinx.serialization)
                // 添加SLF4J的依賴
                implementation ("org.slf4j:slf4j-api:1.7.30")
                runtimeOnly ("org.slf4j:slf4j-simple:1.7.30")
            }
        }
    }
}

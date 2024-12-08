import com.airlibs.app.configureKotlinAndroid
import com.airlibs.app.configurePrintApksTask
import com.airlibs.app.getIntVersion
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension

import org.gradle.kotlin.dsl.configure
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidApplicationConventionPlugin
    : Plugin<Project> {
        override fun apply(target: Project) {
            with(target) {
                with(pluginManager) {
                    apply("com.android.application")
                    apply("org.jetbrains.kotlin.android")
                }

                extensions.configure<ApplicationExtension> {
                    configureKotlinAndroid(this)
                    defaultConfig.targetSdk = getIntVersion("targetSdk")
                }
                extensions.configure<ApplicationAndroidComponentsExtension> {
                    configurePrintApksTask(this)
                }
            }
        }
}
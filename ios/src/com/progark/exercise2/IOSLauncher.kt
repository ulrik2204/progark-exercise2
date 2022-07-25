package com.progark.exercise2

import com.badlogic.gdx.backends.iosrobovm.IOSApplication
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration
import kotlin.jvm.JvmStatic
import org.robovm.apple.foundation.NSAutoreleasePool
import org.robovm.apple.uikit.UIApplication

class IOSLauncher : IOSApplication.Delegate() {
    override fun createApplication(): IOSApplication {
        val config = IOSApplicationConfiguration()
        return IOSApplication(MyGame(), config)
    }

    companion object {
        @JvmStatic
        fun main(argv: Array<String>) {
            val pool = NSAutoreleasePool()
            UIApplication.main<UIApplication, IOSLauncher>(argv, null, IOSLauncher::class.java)
            pool.close()
        }
    }
}
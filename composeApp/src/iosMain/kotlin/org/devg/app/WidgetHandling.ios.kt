package org.devg.app

import androidx.compose.runtime.Composable


interface IOSWidgetResponseProvider {
    fun getDidLaunchFromWidget(): String?

    fun getWidgetsInfo(): List<String>?
}


actual fun getDidLaunchFromWidget(): String? {
    return iOSNativeResponseProvider.getDidLaunchFromWidget()
}

//@Composable
actual fun getWidgetsInfo(): List<String>? {
    return iOSNativeResponseProvider.getWidgetsInfo()
}

lateinit var iOSNativeResponseProvider: IOSWidgetResponseProvider
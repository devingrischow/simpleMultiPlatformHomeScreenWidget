package org.devg.app

import androidx.compose.runtime.Composable



interface IOSWidgetResponseProvider {
    fun getDidLaunchFromWidget(): String?
}


actual fun getDidLaunchFromWidget(): String? {
    return iOSNativeResponseProvider.getDidLaunchFromWidget()
}


lateinit var iOSNativeResponseProvider: IOSWidgetResponseProvider
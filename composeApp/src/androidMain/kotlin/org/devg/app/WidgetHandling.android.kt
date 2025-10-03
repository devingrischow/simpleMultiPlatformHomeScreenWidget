package org.devg.app

import androidx.compose.runtime.Composable

class WidgetLaunchURLHandler {
    companion object {
        var newString:String? = null
    }
}

actual fun getDidLaunchFromWidget(): String? {
    return WidgetLaunchURLHandler.newString
}




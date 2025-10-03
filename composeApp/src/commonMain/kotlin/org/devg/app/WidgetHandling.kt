package org.devg.app

import androidx.compose.runtime.Composable
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

// WidgetHandling



expect fun getDidLaunchFromWidget(): String?



//class AppleReloadShared {
//    companion object {
//        var opened = false
//
//
//
//        fun onAppOpenedFromWidget() {
//            println("App opened from widget!")
//            // call whatever function you need
//            opened = !opened
//            println("Result of Open $opened")
//        }
//    }
//}
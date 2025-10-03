package org.devg.app

import androidx.compose.runtime.Composable
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

// WidgetHandling



expect fun getDidLaunchFromWidget(): String?

///Get Widgets Info Returns a optional string array
/// This is done because there might be No widgets installed
/// * Will Always be valid with at least 1 item(widget) minimum
expect fun getWidgetsInfo():List<String>?
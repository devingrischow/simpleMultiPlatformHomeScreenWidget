package org.devg.app

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProviderInfo
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

class WidgetLaunchURLHandler {
    companion object {
        var newString:String? = null
    }
}

actual fun getDidLaunchFromWidget(): String? {
    return WidgetLaunchURLHandler.newString
}

class ContextObject {
    companion object {
        lateinit var appContext: Context

        fun initContext(context: Context){
            appContext = context
        }
    }
}


//@Composable
actual fun getWidgetsInfo(): List<String>? {
//    val context = LocalContext.current

    val appContext = ContextObject.appContext

    val pinnedWidgetInfoList = mutableListOf<String>()


    val appWidgetManager = AppWidgetManager.getInstance(appContext.applicationContext)

    val installedProviders = appWidgetManager.installedProviders.filter {
        //Filters for widgets only from this app
        it.provider.packageName == appContext.packageName
    }

    //Loops through the apps providers
    for (provider in installedProviders){
        val widgetIDs = appWidgetManager.getAppWidgetIds(provider.provider)
        //Iterate through the loop widgets inside the provider
        for(widgetID in widgetIDs){
            val widgetInfo = appWidgetManager.getAppWidgetInfo(widgetID)
            pinnedWidgetInfoList.add(widgetInfoToString(widgetID, widgetInfo, appContext))
        }

    }


    return pinnedWidgetInfoList
}

fun widgetInfoToString(widgetId: Int, widgetInfo: AppWidgetProviderInfo, context: Context): String{
    val label = widgetInfo.loadLabel(context.packageManager).toString()

    val returnLabel = "WidgetID:$widgetId-ShortClassName:${widgetInfo.provider.shortClassName}-WidgetLabel:$label"

    return returnLabel
}
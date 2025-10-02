package org.devg.app

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.edit


actual fun getNativeResponse(): String {
    return "Hello From Android!"
}


@Composable
actual fun getCountFromUserPreference(collectionID: String, valueKey: String): Int {
    val context = LocalContext.current

    val sharedPrefs = context.getSharedPreferences(collectionID, Context.MODE_PRIVATE)
    val currentCount = sharedPrefs
        .getInt(valueKey, 0)

    print("Current Count: $currentCount\n")

    return currentCount
}

@SuppressLint("ComposableNaming")
@Composable
actual fun setCountToUserPreference(collectionID: String, valueKey: String, newValue:Int) {
    val context = LocalContext.current

    val sharedPrefs = context.getSharedPreferences(collectionID, Context.MODE_PRIVATE)

    sharedPrefs
        .edit {
            putInt(valueKey, newValue)
        }
}
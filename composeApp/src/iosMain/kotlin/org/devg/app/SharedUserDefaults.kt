package org.devg.app

import androidx.compose.runtime.Composable

//import iosApp.SharedUserDefaultsIOSBridge

actual fun getNativeResponse(): String {
    return nativeIOSResponseProvider.getNativeResponse()
}

@Composable
actual fun getCountFromUserPreference(collectionID: String, valueKey: String): Int {
    return nativeIOSResponseProvider.getCountFromUserPreference(collectionID, valueKey)
}

@Composable
actual fun setCountToUserPreference(collectionID: String, valueKey: String, newValue: Int) {
    return nativeIOSResponseProvider.setCountToUserPreference(collectionID, valueKey, newValue)
}

//Connection Code Assistance From: https://www.youtube.com/watch?v=h_xDOkqLQbA
interface IOSResponseProvider{
    fun getNativeResponse(): String

    fun getCountFromUserPreference(collectionID: String, valueKey: String): Int

    fun setCountToUserPreference(collectionID: String, valueKey: String, newValue: Int)
}


lateinit var nativeIOSResponseProvider: IOSResponseProvider








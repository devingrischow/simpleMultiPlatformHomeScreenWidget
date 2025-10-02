package org.devg.app

import androidx.compose.runtime.Composable

expect fun getNativeResponse(): String


//Function for handling counting Data between widget and app
@Composable
expect fun getCountFromUserPreference(collectionID:String, valueKey:String): Int


@Composable
expect fun setCountToUserPreference(collectionID:String, valueKey:String, newValue:Int)
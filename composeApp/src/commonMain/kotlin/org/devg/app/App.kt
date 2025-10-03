package org.devg.app


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme {

        var sendVals by remember { mutableStateOf("") }

        val retroCount = getCountFromUserPreference("group.example.widget_group", CounterHandler().countKey)


//        var didLaunchFromURL by remember { mutableStateOf<String>(getDidLaunchFromWidget() ?: "") }

        var didLaunchFromURL by remember { mutableStateOf<String>("") }

        //Get the curr count, increase it, and send it
        var currCount by remember { mutableStateOf(0) }


        LaunchedEffect(Unit){
            currCount = retroCount

            print("At Launch Value of Launch String: $didLaunchFromURL\n")

            didLaunchFromURL = getDidLaunchFromWidget() ?: ""

        }

        SideEffect {
            didLaunchFromURL = getDidLaunchFromWidget() ?: ""
        }




        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text("Current Count")

            Text(currCount.toString())

            Text("Did Launch From Widget? $didLaunchFromURL")

            //Plus Button
            Button(onClick = {
                print("Pressed Button\n")

                currCount = CounterHandler().incrementValue(currCount)

                sendVals = "send_count"
            }){
                Text("+")
                //Bottom Of Button
            }
        }


        when(sendVals) {
            "send_count" -> {
                print("Send Count Updating\n")
                //Set the user preferences, and change the send vals value back to nil
                setCountToUserPreference("group.example.widget_group", CounterHandler().countKey, currCount)

                sendVals = ""
            }
        }



    }
}
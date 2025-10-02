package org.devg.widgets

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent

//Glance Layouts+Elements
import androidx.glance.Button
import androidx.glance.GlanceModifier
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.text.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.LocalContext
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import org.devg.app.CounterHandler
import androidx.core.content.edit
import androidx.datastore.preferences.core.intPreferencesKey

val INSTANCEKEY = "group.example.widget_group"

object SimpleCounterWidget: GlanceAppWidget() {

    val countKey = intPreferencesKey(CounterHandler().countKey)



    override suspend fun provideGlance(
        context: Context,
        id: GlanceId
    ) {
        provideContent {
            Content()
        }



    }

    @Composable
    fun Content() {
        val count = currentState(key = countKey) ?: 0
        Column(
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = GlanceModifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            Text(text = "Pressed")

            //Counter Variable
            Text(text = count.toString())

            Text(text = "Times")

            //Counter Button
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalAlignment = Alignment.CenterHorizontally,

                modifier = GlanceModifier
            ) {

                Spacer()

                Button(
                    text = "-",
                    onClick = actionRunCallback(DecrementActionCallback::class.java),
                    modifier = GlanceModifier



                )

                Spacer()

                Button(
                    text = "+",
                    onClick = actionRunCallback(IncrementActionCallback::class.java),
                    modifier = GlanceModifier




                )

                Spacer()
            }


        }

    }


}





class SimpleCounterWidgetReceiver: GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = SimpleCounterWidget
}

//Increment Action

class IncrementActionCallback: ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        updateAppWidgetState(context, glanceId) { prefs ->
            val sharedPrefs = context.getSharedPreferences(INSTANCEKEY, Context.MODE_PRIVATE)
            val currentCount = sharedPrefs
                .getInt(CounterHandler().countKey, 0)


                val newCount = CounterHandler().incrementValue(currentCount)

                //Update the storage with the NEw Value
                sharedPrefs
                    .edit {
                        putInt(CounterHandler().countKey, newCount)
                    }

            //Update the Widget Preference
            prefs[SimpleCounterWidget.countKey] = newCount
        }
        SimpleCounterWidget.update(context, glanceId)

    }


}

class DecrementActionCallback: ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        updateAppWidgetState(context, glanceId) { prefs ->
            val sharedPrefs = context.getSharedPreferences(INSTANCEKEY, Context.MODE_PRIVATE)

            val currentCount = sharedPrefs
                .getInt(CounterHandler().countKey, 0)


            val newCount = CounterHandler().decrementValue(currentCount)

            //Update the storage with the NEW Value
            sharedPrefs
                .edit {
                    putInt(CounterHandler().countKey, newCount)
                }

            prefs[SimpleCounterWidget.countKey] = newCount

        }
        SimpleCounterWidget.update(context, glanceId)
    }

}
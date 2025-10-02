package org.devg.app

class CounterHandler {

    val appleGroupID:String = "group.example.widget_group"

    val androidID:String = ""


    val countKey = "count"


    ///Takes the curr Value of the counter and increments it by 1
    fun incrementValue(currVal:Int):Int{
        print("Incrementing Count\n")
        val newCount =  currVal + 1
        print("New Count Value: $newCount\n")
        //Simply Increments new value
        return newCount
    }

    fun decrementValue(currVal:Int):Int{
        print("Decrement Count\n")
        val newCount =  currVal - 1
        print("New Count Value: $newCount\n")
        //Simply decrement new value
        return newCount
    }


}
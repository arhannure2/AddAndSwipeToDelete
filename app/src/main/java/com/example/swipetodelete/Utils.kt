package com.example.swipetodelete

import android.app.Activity
import android.content.Intent

/*
Created by ​
Hannure Abdulrahim


on 10/19/2021.
 */

fun<A : Activity> Activity.startNewActivity(activity: Class<A>){
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)

    }
}
package org.devconmyanmar.devconyangon.utils

import android.content.Context
import android.content.Intent

fun <T> Context.openActivity(it: Class<T>) {
    val intent = Intent(this, it)
    startActivity(intent)
}
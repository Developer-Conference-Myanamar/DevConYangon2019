package org.devconmyanmar.devconyangon.utils

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun <T> Context.openActivity(it: Class<T>) {
    val intent = Intent(this, it)
    startActivity(intent)
}

fun RecyclerView.setUpRecycler(context: Context) {
    hasFixedSize()
    layoutManager = LinearLayoutManager(context)
}

fun RecyclerView.setUpGrid(context: Context, column: Int) {
    hasFixedSize()
    layoutManager = GridLayoutManager(context, column)
}
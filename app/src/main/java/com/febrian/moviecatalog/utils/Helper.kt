package com.febrian.moviecatalog.utils

import android.content.Context
import android.widget.Toast

class Helper(private val c: Context) {

    fun showToast(message: String) {
        Toast.makeText(c, message, Toast.LENGTH_SHORT).show()
    }
}
package com.example.unittest.utils

import android.view.View

fun View.onHide() {
    this.visibility = View.GONE
}

fun View.onShow() {
    this.visibility = View.VISIBLE
}
package com.siziksu.kow.common.extensions

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

fun AppCompatActivity.applyToolBarStyleWithHome(toolbar: Toolbar) {
    this.setSupportActionBar(toolbar)
    this.supportActionBar?.setDisplayShowHomeEnabled(true)
}

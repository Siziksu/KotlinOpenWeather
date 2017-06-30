package com.siziksu.kow.presenter

import android.app.Activity

interface IBaseView {

    val activity: Activity

    fun showProgress(value: Boolean)
}

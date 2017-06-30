package com.siziksu.kow.presenter

abstract class BasePresenter<V : IBaseView> {

    protected var view: V? = null

    fun register(view: V) {
        this.view = view
    }

    fun unregister() {
        this.view = null
    }

    protected val isViewRegistered: Boolean
        get() = view != null
}

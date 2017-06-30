package com.siziksu.kow.presenter.weather

import com.siziksu.kow.presenter.BasePresenter
import com.siziksu.kow.presenter.IBaseView

abstract class IMainPresenter<V : IBaseView> : BasePresenter<V>() {

    abstract fun getWeather(city: String)
}

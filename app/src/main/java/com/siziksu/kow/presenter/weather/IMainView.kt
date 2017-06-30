package com.siziksu.kow.presenter.weather

import com.siziksu.kow.common.model.weather.response.Weather
import com.siziksu.kow.presenter.IBaseView

interface IMainView : IBaseView {

    fun showWeather(weather: Weather)

    fun showError()
}

package com.siziksu.kow.provider

import android.content.Context
import com.siziksu.kow.data.weather.WeatherClientAdapter
import com.siziksu.kow.domain.weather.WeatherRequests
import com.siziksu.kow.domain.weather.IWeatherRequests
import com.siziksu.kow.presenter.weather.IMainPresenter
import com.siziksu.kow.presenter.weather.IMainView
import com.siziksu.kow.presenter.weather.MainPresenter

class ProviderModule(private val context: Context) {

    val mainPresenter: IMainPresenter<IMainView>
        get() = MainPresenter().setContext(context).setWeatherRequest(weatherRequests)

    private val weatherRequests: IWeatherRequests
        get() = WeatherRequests().setService(WeatherClientAdapter().getService(context))
}

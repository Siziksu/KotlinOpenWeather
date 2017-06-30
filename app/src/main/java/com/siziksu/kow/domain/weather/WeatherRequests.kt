package com.siziksu.kow.domain.weather

import com.siziksu.kow.BuildConfig
import com.siziksu.kow.common.model.weather.OpenWeather
import com.siziksu.kow.data.weather.IWeatherClientService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class WeatherRequests : IWeatherRequests {

    companion object {
        private const val METRIC = "metric"
    }

    private var weatherClientService: IWeatherClientService? = null

    fun setService(weatherClientService: IWeatherClientService): IWeatherRequests {
        this.weatherClientService = weatherClientService
        return this
    }

    override fun getWeather(city: String): Observable<OpenWeather> {
        return weatherClientService
                ?.getWeather(city, BuildConfig.OPEN_WEATHER_API_KEY, METRIC)
                ?.subscribeOn(Schedulers.io()) ?: Observable.create { }
    }
}

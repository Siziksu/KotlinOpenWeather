package com.siziksu.kow.domain.weather

import com.siziksu.kow.common.model.weather.OpenWeather
import io.reactivex.Observable

interface IWeatherRequests {

    fun getWeather(city: String): Observable<OpenWeather>
}

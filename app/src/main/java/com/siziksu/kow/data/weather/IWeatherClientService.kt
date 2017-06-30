package com.siziksu.kow.data.weather

import com.siziksu.kow.common.model.weather.OpenWeather
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherClientService {

    companion object {
        private const val URI = "data/2.5/weather"
    }

    @GET(URI)
    fun getWeather(
            @Query("q") city: String?,
            @Query("apikey") apiKey: String,
            @Query("units") units: String
    ): Observable<OpenWeather>
}

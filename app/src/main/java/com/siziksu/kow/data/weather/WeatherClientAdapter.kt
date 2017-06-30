package com.siziksu.kow.data.weather

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class WeatherClientAdapter {

    companion object {
        private const val BASE_URL = "http://api.openweathermap.org/"
        private const val CONNECTION_TIMEOUT = 5L
    }

    fun getService(context: Context): IWeatherClientService {
        // 10 * 1024 * 1024; // 10 MiB
        val cacheSize: Long = 10485760
        val cache: Cache = Cache(context.cacheDir, cacheSize)

        val logging: HttpLoggingInterceptor = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(logging)
        val client: OkHttpClient = okHttpClientBuilder.build()

        val gson: Gson = GsonBuilder().create()
        val factory: GsonConverterFactory = GsonConverterFactory.create(gson)

        val retorfitBuilder: Retrofit.Builder = Retrofit.Builder()
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
        retorfitBuilder.client(client)
        val retrofit: Retrofit = retorfitBuilder.build()

        return retrofit.create(IWeatherClientService::class.java)
    }
}

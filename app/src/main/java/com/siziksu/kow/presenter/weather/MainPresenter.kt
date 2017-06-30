package com.siziksu.kow.presenter.weather

import android.content.Context
import android.util.Log
import com.siziksu.kow.R
import com.siziksu.kow.common.manger.ConnectionManager
import com.siziksu.kow.common.model.weather.OpenWeather
import com.siziksu.kow.common.model.weather.response.Weather
import com.siziksu.kow.common.utils.DateUtils
import com.siziksu.kow.domain.weather.IWeatherRequests
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class MainPresenter : IMainPresenter<IMainView>() {

    companion object {
        private const val TAG = "MainPresenter"
    }

    private val connectionManager by lazy { ConnectionManager(context) }

    private var disposable: Disposable? = null
    private var weatherRequests: IWeatherRequests? = null
    private var context: Context? = null

    fun setWeatherRequest(weatherRequests: IWeatherRequests): MainPresenter {
        this.weatherRequests = weatherRequests
        return this
    }

    fun setContext(context: Context): MainPresenter {
        this.context = context
        return this
    }

    override fun getWeather(city: String) {
        if (disposable != null) {
            disposable?.dispose()
            disposable = null
        }
        val connected: Boolean = connectionManager.doIfThereIsConnection {
            view?.showProgress(true)
            disposable = weatherRequests
                    ?.getWeather(city)
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe(
                            { it -> view?.showWeather(processResponse(it)) },
                            { it ->
                                Log.d(TAG, it.message, it)
                                view?.showError()
                                view?.showProgress(false)
                            },
                            { view?.showProgress(false) }
                    ) ?: onRequestNull()
        }
        if (connected) {
            Log.i(TAG, context?.getString(R.string.connection_ok) ?: onContextNull())
        } else {
            view?.showError()
            Log.i(TAG, context?.getString(R.string.connection_error) ?: onContextNull())
        }
    }

    private fun onRequestNull(): Disposable? {
        view?.showError()
        view?.showProgress(false)
        Log.i(TAG, "Request is null")
        return Observable.create<Weather> { }.subscribe()
    }

    private fun onContextNull(): String? {
        return "Context is null"
    }

    private fun processResponse(response: OpenWeather): Weather {
        val weather = Weather()
        weather.place = response.name
        weather.temperature = String.format(view?.activity?.getString(R.string.temperature) ?: "", response.main?.temperature)
        weather.time = String.format(view?.activity?.getString(R.string.temperature_update_time) ?: "", DateUtils().currentTime)
        return weather
    }
}

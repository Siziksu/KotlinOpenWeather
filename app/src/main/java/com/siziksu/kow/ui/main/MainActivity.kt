package com.siziksu.kow.ui.main

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.siziksu.kow.R
import com.siziksu.kow.common.extensions.applyToolBarStyleWithHome
import com.siziksu.kow.common.model.weather.response.Weather
import com.siziksu.kow.provider.ProviderModule
import com.siziksu.kow.presenter.weather.IMainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IMainView {

    companion object {
        private const val TAG = "MainActivity"
        private const val CITY = "Barcelona,Spain"
    }

    private val presenter by lazy { ProviderModule(this).mainPresenter }
    private val toolbar by lazy { findViewById(R.id.defaultToolbar) as Toolbar }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        applyToolBarStyleWithHome(toolbar)
    }

    override fun onResume() {
        super.onResume()
        presenter.register(this)
        presenter.getWeather(CITY)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unregister()
    }

    override val activity: Activity
        get() = this

    override fun showWeather(weather: Weather) {
        isAllGood(true)
        textViewPlace.text = weather.place
        textViewTemperature.text = weather.temperature
        textViewUpdate.text = weather.time
    }

    override fun showError() {
        isAllGood(false)
    }

    override fun showProgress(value: Boolean) {
        progressBar.visibility = if (value) View.VISIBLE else View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh -> presenter.getWeather(CITY)
            else -> {
            }
        }
        return false
    }

    private fun isAllGood(value: Boolean) {
        textViewPlace.visibility = if (value) View.VISIBLE else View.GONE
        textViewTemperature.visibility = if (value) View.VISIBLE else View.GONE
        textViewUpdate.visibility = if (value) View.VISIBLE else View.GONE
        textViewError.visibility = if (value) View.GONE else View.VISIBLE
    }
}


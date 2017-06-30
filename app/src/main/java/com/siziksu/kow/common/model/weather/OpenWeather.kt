package com.siziksu.kow.common.model.weather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OpenWeather {

    @SerializedName("main")
    @Expose
    var main: Main? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
}

package com.siziksu.kow.common.model.weather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Main {

    @SerializedName("temp")
    @Expose
    var temperature: Double? = null
}

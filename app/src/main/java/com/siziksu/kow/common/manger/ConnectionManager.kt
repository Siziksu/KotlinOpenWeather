package com.siziksu.kow.common.manger

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import com.siziksu.kow.common.model.connection.Connection

class ConnectionManager(private val context: Context?) {

    companion object {
        private const val TAG = "ConnectionManager"
    }

    fun getConnection(): Connection? {
        if (context == null) return null
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var networkInfo: NetworkInfo? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val networks = connectivityManager.allNetworks
            for (mNetwork in networks) {
                networkInfo = connectivityManager.getNetworkInfo(mNetwork)
                if (networkInfo.state == NetworkInfo.State.CONNECTED) {
                    break
                }
            }
        } else {
            val networkInfos = connectivityManager.allNetworkInfo
            if (networkInfos != null) {
                for (info in networkInfos) {
                    if (info.state == NetworkInfo.State.CONNECTED) {
                        networkInfo = info
                        break
                    }
                }
            }
        }
        if (networkInfo != null
                && networkInfo.detailedState != NetworkInfo.DetailedState.DISCONNECTED
                && networkInfo.state != NetworkInfo.State.DISCONNECTED
                && networkInfo.detailedState != NetworkInfo.DetailedState.CONNECTING
                && networkInfo.state != NetworkInfo.State.CONNECTING) {
            return Connection(true, networkInfo.typeName)
        } else {
            return Connection(false, null)
        }
    }

    fun doIfThereIsConnection(func: () -> Unit): Boolean {
        if (context == null) return false
        val isConnected = getConnection()?.isConnected ?: false
        if (!isConnected) {
            return false
        } else {
            func()
            return true
        }
    }
}

package com.abrar.kitabisanonton.external

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.abrar.kitabisanonton.R

fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    //Internet connectivity check in Android Q
    val networks = connectivityManager.allNetworks
    var hasInternet = false
    if (networks.isNotEmpty()) {
        for (network in networks) {
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            if (networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true) hasInternet = true
        }
    }
    return hasInternet
}

fun Context.showDialog(title: String, activity: Activity) {
    val dialog = Dialog(activity)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(false)
    dialog.setContentView(R.layout.dialog_layout)
    val body = dialog.findViewById(R.id.text_title_dialog) as TextView
    body.text = title
    val yesBtn = dialog.findViewById(R.id.button_yes) as Button
    val noBtn = dialog.findViewById(R.id.button_no) as Button
    yesBtn.setOnClickListener {
        dialog.dismiss()
    }
    noBtn.setOnClickListener { dialog.dismiss() }
    dialog.show()

}
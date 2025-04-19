package com.aroy.dailypulsenewsapp

import android.content.res.Resources
import android.os.Build
import kotlin.math.round

class AndroidPlatform : Platform {
    override val osName: String
        get() = "Android"
    override val osVersion: String
        get() = "${Build.VERSION.SDK_INT}"
    override val deviceModel: String
        get() = "${Build.MANUFACTURER} - ${Build.MODEL}"
    override val density: String
        get() = "${round(Resources.getSystem().displayMetrics.density)}"

}

actual fun getPlatform(): Platform = AndroidPlatform()
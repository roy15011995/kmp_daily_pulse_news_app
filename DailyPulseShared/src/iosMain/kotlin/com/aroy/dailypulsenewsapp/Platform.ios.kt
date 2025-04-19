package com.aroy.dailypulsenewsapp

import platform.UIKit.UIDevice
import platform.UIKit.UIScreen

class IOSPlatform: Platform {
    override val osName: String
        get() = UIDevice.currentDevice().systemName
    override val osVersion: String
        get() = UIDevice.currentDevice().systemVersion
    override val deviceModel: String
        get() = UIDevice.currentDevice().model
    override val density: String
        get() = UIScreen.mainScreen.scale.toString()

}

actual fun getPlatform(): Platform = IOSPlatform()
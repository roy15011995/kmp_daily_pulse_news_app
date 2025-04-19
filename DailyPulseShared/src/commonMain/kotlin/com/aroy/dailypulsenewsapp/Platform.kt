package com.aroy.dailypulsenewsapp

interface Platform {
    val osName: String
    val osVersion: String
    val deviceModel: String
    val density: String
}

expect fun getPlatform(): Platform
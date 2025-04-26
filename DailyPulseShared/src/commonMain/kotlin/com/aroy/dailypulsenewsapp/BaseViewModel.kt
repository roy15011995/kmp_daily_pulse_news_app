package com.aroy.dailypulsenewsapp

import kotlinx.coroutines.CoroutineScope

/**
 * Created by amitroy on Date : 20/04/25
 */
expect open class BaseViewModel() {
    val scope: CoroutineScope
}
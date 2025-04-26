package com.aroy.dailypulsenewsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

/**
 * Created by amitroy on Date : 20/04/25
 */
actual open class BaseViewModel: ViewModel() {
    actual val scope = viewModelScope
}
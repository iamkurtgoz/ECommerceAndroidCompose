package com.iamkurtgoz.ecommerceandroid.ui.splash

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.iamkurtgoz.application.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    application: Application
) : BaseViewModel(application) {

    private val _state: MutableState<SplashState> = mutableStateOf(SplashState.None)
    val state: State<SplashState> = _state

    init {
        viewModelScope.launch {
            //delay(2500)
            _state.value = SplashState.GoToHomeScreen
        }
    }

    fun clearState(){
        _state.value = SplashState.None
    }
}

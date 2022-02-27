package com.iamkurtgoz.ecommerceandroid.ui.main

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import com.iamkurtgoz.application.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application
) : BaseViewModel(application) {

}

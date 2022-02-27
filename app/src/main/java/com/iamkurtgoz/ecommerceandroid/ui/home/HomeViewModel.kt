package com.iamkurtgoz.ecommerceandroid.ui.home

import android.app.Application
import com.iamkurtgoz.application.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application
) : BaseViewModel(application) {

}

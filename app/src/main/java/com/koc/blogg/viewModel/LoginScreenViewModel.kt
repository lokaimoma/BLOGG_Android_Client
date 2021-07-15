package com.koc.blogg.viewModel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
Created by kelvin_clark on 7/15/2021 1:28 AM
 */
@HiltViewModel
class LoginScreenViewModel @Inject constructor(): ViewModel() {

    var email = ""
    var password = ""

}
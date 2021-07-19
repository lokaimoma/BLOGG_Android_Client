package com.koc.blogg.viewModel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
Created by kelvin_clark on 7/19/2021 2:50 AM
 */
@HiltViewModel
class SignUpScreenViewModel @Inject constructor(): ViewModel() {
    var email = ""
    var password = ""
    var confirmPassword = ""
    var username = ""
}
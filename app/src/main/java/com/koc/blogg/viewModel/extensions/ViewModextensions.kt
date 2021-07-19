package com.koc.blogg.viewModel.extensions

import com.koc.blogg.model.remote.UserLogin
import com.koc.blogg.util.PreferenceManager

/**
Created by kelvin_clark on 7/19/2021 3:47 AM
 */

suspend fun saveCredentials(data: UserLogin, preferenceManager: PreferenceManager) {
    preferenceManager.apply {
        updateUserId(data.id)
        updateUserEmail(data.email)
        updateUserName(data.username)
    }
}
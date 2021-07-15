package com.koc.blogg.util


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.koc.blogg.util.Constants.DATASTORE_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
Created by kelvin_clark on 7/15/2021 11:40 PM
 */
@Singleton
class PreferenceManager @Inject constructor(@ApplicationContext private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

    val userId = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferenceKeys.userId]
        }

    val userEmail = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferenceKeys.userEmail]
        }

    val userName = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferenceKeys.userName]
        }

    suspend fun updateUserId(userId: Int) {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.userId] = userId
        }
    }

    suspend fun updateUserEmail(userEmail: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.userEmail] = userEmail
        }
    }

    suspend fun updateUserName(userName: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.userName] = userName
        }
    }

    private object PreferenceKeys {
        val userId = intPreferencesKey("user_id")
        val userEmail = stringPreferencesKey("user_email")
        val userName = stringPreferencesKey("user_name")
    }
}
package com.airlibs.data.sources.local.dataStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.airlibs.domain.di.AirLibsDispatchers
import com.airlibs.domain.di.Dispatcher
import com.airlibs.domain.utils.Constants.INVALID_ENTITY_ID
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AirLibsDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    @Dispatcher(AirLibsDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) {
    fun getString(keyName: String): Flow<String?> =
        dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                }
            }
            .map { preferences ->
                preferences[stringPreferencesKey(keyName)]
            }

    suspend fun putString(keyName: String, value: String) {
        dataStore.edit {
            it[stringPreferencesKey(keyName)] = value
        }
    }

    fun getBoolean(keyName: String): Flow<Boolean> =
        dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                }
            }
            .map { preferences ->
                preferences[booleanPreferencesKey(keyName)] ?: false
            }

    suspend fun putBoolean(keyName: String, value: Boolean) {
        dataStore.edit {
            it[booleanPreferencesKey(keyName)] = value
        }
    }

    fun getInt(keyName: String): Flow<Int> =
        dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                }
            }
            .map { preferences ->
                preferences[intPreferencesKey(keyName)] ?: INVALID_ENTITY_ID
            }

    suspend fun putInt(keyName: String, value: Int) {
        dataStore.edit {
            it[intPreferencesKey(keyName)] = value
        }
    }

    fun getLong(keyName: String): Flow<Long> =
        dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                }
            }
            .map { preferences ->
                preferences[longPreferencesKey(keyName)] ?: 0
            }

    suspend fun putLong(keyName: String, value: Long) {
        dataStore.edit {
            it[longPreferencesKey(keyName)] = value
        }
    }

    suspend fun clearData() {
        CoroutineScope(ioDispatcher).launch {
            dataStore.edit {
                it.clear()
            }
        }
    }
}
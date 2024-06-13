package org.d3if0041.assessment.inventorytoko.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "setting_preferences"
)

class SettingsDataStore(private val context: Context) {
    private val dataStore: DataStore<Preferences> = context.dataStore

    fun layoutFlow(key: String): Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[booleanPreferencesKey(key)] ?: true
        }

    suspend fun saveLayout(isList: Boolean, key: String) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = isList
        }
    }
}
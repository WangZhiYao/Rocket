package com.yizhenwind.rocket.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import com.yizhenwind.rocket.core.logger.ILogger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/30
 */
class DataStoreManager @Inject constructor(
    private val logger: ILogger,
    private val dataStore: DataStore<Preferences>
) {

    fun getInt(name: String, default: Int): Flow<Int> = get(intPreferencesKey(name), default)

    suspend fun setInt(name: String, value: Int) {
        set(intPreferencesKey(name), value)
    }

    private fun <T> get(key: Preferences.Key<T>, default: T): Flow<T> =
        dataStore.data
            .catch {
                logger.e(it)
                emit(emptyPreferences())
            }
            .map { preferences ->
                preferences[key] ?: default
            }

    private suspend fun <T> set(key: Preferences.Key<T>, value: T) {
        dataStore.edit { preferences -> preferences[key] = value }
    }

}
package com.yizhenwind.rocket.core.datastore.ext

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/10/20
 */
val Context.rocketDataStore: DataStore<Preferences> by preferencesDataStore(name = "datastore_pref_rocket")

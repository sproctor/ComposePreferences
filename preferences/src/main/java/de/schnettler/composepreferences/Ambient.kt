package de.schnettler.composepreferences

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableAmbient
import androidx.compose.runtime.Providers
import androidx.compose.runtime.ambientOf
import androidx.compose.runtime.remember
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.ExperimentalCoroutinesApi

val DataSourceAmbient: ProvidableAmbient<DataStore<Preferences>> =
    ambientOf { error("No preferences found") }

@ExperimentalCoroutinesApi
@Composable
fun ProvidePreferences(
    context: Context,
    content: @Composable () -> Unit,
) {
    val dataStore = remember(context) {
        context.createDataStore("preferences")
    }
    Providers(DataSourceAmbient provides dataStore) {
        content()
    }
}

val PreferenceEnabledAmbient: ProvidableAmbient<Boolean> =
    ambientOf { true }
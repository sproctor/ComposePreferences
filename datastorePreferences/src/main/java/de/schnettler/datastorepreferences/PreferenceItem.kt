package de.schnettler.datastorepreferences

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey

interface BasePreferenceItem

interface PreferenceItem<T> : BasePreferenceItem {
    val title: String
    val summary: String?
    val key: String
    val singleLineTitle: Boolean
    val icon: ImageVector?
    val enabled: Boolean
}

interface ListPreferenceItem : PreferenceItem<String> {
    val entries: Map<String, String>
}

data class StringPreferenceItem(
    override val title: String,
    override val summary: String? = null,
    override val key: String,
    override val singleLineTitle: Boolean = true,
    override val icon: ImageVector? = null,
    override val enabled: Boolean = true,
    val defaultValue: String = "",
) : PreferenceItem<String> {
    val prefKey = stringPreferencesKey(key)
}

data class SwitchPreferenceItem(
    override val title: String,
    override val summary: String? = null,
    override val key: String,
    override val singleLineTitle: Boolean = true,
    override val icon: ImageVector? = null,
    override val enabled: Boolean = true,
    val defaultValue: Boolean = false,
): PreferenceItem<Boolean> {
    val prefKey = booleanPreferencesKey(key)
}

data class SingleListPreferenceItem(
    override val title: String,
    override val summary: String? = null,
    override val key: String,
    override val singleLineTitle: Boolean = true,
    override val icon: ImageVector? = null,
    override val enabled: Boolean = true,
    override val entries: Map<String, String>,
    val defaultValue: String = "",
) : ListPreferenceItem {
    val prefKey = stringPreferencesKey(key)
}

data class MultiListPreferenceItem(
    override val title: String,
    override val summary: String? = null,
    override val key: String,
    override val singleLineTitle: Boolean = true,
    override val icon: ImageVector? = null,
    override val enabled: Boolean = true,
    override val entries: Map<String, String>,
    val defaultValue: Set<String> = emptySet()
) : ListPreferenceItem {
    val prefKey = stringSetPreferencesKey(key)
}

data class SeekbarPreferenceItem(
    override val title: String,
    override val summary: String? = null,
    override val key: String,
    override val singleLineTitle: Boolean = true,
    override val icon: ImageVector? = null,
    override val enabled: Boolean = true,
    val defaultValue: Float = 0F,
    val valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    val steps: Int = 0,
    val valueRepresentation: (Float) -> String
) : PreferenceItem<Float> {
    val prefKey = floatPreferencesKey(key)
}

data class CustomPreferenceItem(
    override val title: String,
    override val summary: String? = null,
    override val key: String,
    override val singleLineTitle: Boolean = true,
    override val icon: ImageVector? = null,
    override val enabled: Boolean = true,
    val onClick: () -> Unit = {}
) : PreferenceItem<Unit>

data class PreferenceGroup(
    val title: String,
    val enabled: Boolean = true,
    val content: List<PreferenceItem<*>>
) : BasePreferenceItem
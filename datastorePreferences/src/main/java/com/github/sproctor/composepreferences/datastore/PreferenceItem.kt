package com.github.sproctor.composepreferences.datastore

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.datastore.preferences.core.*

public interface PreferenceItem {
    public val title: String
    public val enabled: Boolean
    public val summary: String?
    public val icon: ImageVector?
    public val singleLineTitle: Boolean
}

public interface ListPreferenceItem : PreferenceItem {
    public val entries: Map<String, String>
}

public data class TextPreferenceItem(
    override val title: String,
    override val summary: String? = null,
    override val singleLineTitle: Boolean = true,
    override val icon: ImageVector? = null,
    override val enabled: Boolean = true,
    val isPassword: Boolean = false,
    val key: String,
) : PreferenceItem {
    val prefKey: Preferences.Key<String> = stringPreferencesKey(key)
}

public data class SwitchPreferenceItem(
    override val title: String,
    override val summary: String? = null,
    override val singleLineTitle: Boolean = true,
    override val icon: ImageVector? = null,
    override val enabled: Boolean = true,
    val key: String,
): PreferenceItem {
    val prefKey: Preferences.Key<Boolean> = booleanPreferencesKey(key)
}

public data class SingleListPreferenceItem(
    override val title: String,
    override val summary: String? = null,
    override val singleLineTitle: Boolean = true,
    override val icon: ImageVector? = null,
    override val enabled: Boolean = true,
    override val entries: Map<String, String>,
    val key: String,
) : ListPreferenceItem {
    val prefKey: Preferences.Key<String> = stringPreferencesKey(key)
}

public data class MultiListPreferenceItem(
    override val title: String,
    override val summary: String? = null,
    override val singleLineTitle: Boolean = true,
    override val icon: ImageVector? = null,
    override val enabled: Boolean = true,
    override val entries: Map<String, String>,
    val key: String,
) : ListPreferenceItem {
    val prefKey: Preferences.Key<Set<String>> = stringSetPreferencesKey(key)
}

public data class SeekbarPreferenceItem(
    override val title: String,
    override val summary: String? = null,
    override val singleLineTitle: Boolean = true,
    override val icon: ImageVector? = null,
    override val enabled: Boolean = true,
    val defaultValue: Float = 0F,
    val valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    val steps: Int = 0,
    val valueRepresentation: (Float) -> String,
    val key: String,
) : PreferenceItem {
    val prefKey: Preferences.Key<Float> = floatPreferencesKey(key)
}

public data class SimplePreferenceItem(
    override val title: String,
    override val summary: String? = null,
    override val singleLineTitle: Boolean = true,
    override val icon: ImageVector? = null,
    override val enabled: Boolean = true,
    val onClick: () -> Unit = {}
) : PreferenceItem

public data class PreferenceGroupItem(
    override val title: String,
    override val summary: String? = null,
    override val singleLineTitle: Boolean = true,
    override val icon: ImageVector? = null,
    override val enabled: Boolean = true,
    val items: List<PreferenceItem>,
) : PreferenceItem

public object PreferenceDivider : PreferenceItem {
    override val title: String = ""
    override val summary: String? = null
    override val singleLineTitle: Boolean = true
    override val icon: ImageVector? = null
    override val enabled: Boolean = true
}
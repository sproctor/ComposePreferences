package com.github.sproctor.composepreferences.settings

import androidx.compose.runtime.Composable

public interface PreferenceItem {
    public val title: String
    public val enabled: Boolean
    public val summary: String?
    public val icon: (@Composable () -> Unit)?
    public val singleLineTitle: Boolean
}

public data class TextPreferenceItem(
    override val title: String,
    override val summary: String? = null,
    override val singleLineTitle: Boolean = true,
    override val icon: (@Composable () -> Unit)? = null,
    override val enabled: Boolean = true,
    val isPassword: Boolean = false,
    val key: String,
) : PreferenceItem

public data class SwitchPreferenceItem(
    override val title: String,
    override val summary: String? = null,
    override val singleLineTitle: Boolean = true,
    override val icon: (@Composable () -> Unit)? = null,
    override val enabled: Boolean = true,
    val key: String,
): PreferenceItem

public data class SingleListPreferenceItem(
    override val title: String,
    override val summary: String? = null,
    override val singleLineTitle: Boolean = true,
    override val icon: (@Composable () -> Unit)? = null,
    override val enabled: Boolean = true,
    val emptyText: String? = null,
    val entries: Map<String?, String>,
    val key: String,
) : PreferenceItem

public data class MultiListPreferenceItem(
    override val title: String,
    override val summary: String? = null,
    override val singleLineTitle: Boolean = true,
    override val icon: (@Composable () -> Unit)? = null,
    override val enabled: Boolean = true,
    val emptyText: String? = null,
    val entries: Map<String, String>,
    val key: String,
) : PreferenceItem {
    init {
        entries.keys.forEach { require(!it.contains(",")) }
    }
}

public data class SeekbarPreferenceItem(
    override val title: String,
    override val summary: String? = null,
    override val singleLineTitle: Boolean = true,
    override val icon: (@Composable () -> Unit)? = null,
    override val enabled: Boolean = true,
    val defaultValue: Float = 0F,
    val valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    val steps: Int = 0,
    val valueRepresentation: (Float) -> String,
    val key: String,
) : PreferenceItem

public data class SimplePreferenceItem(
    override val title: String,
    override val summary: String? = null,
    override val singleLineTitle: Boolean = true,
    override val icon: (@Composable () -> Unit)? = null,
    override val enabled: Boolean = true,
    val onClick: () -> Unit = {}
) : PreferenceItem

public data class PreferenceGroupItem(
    override val title: String,
    override val summary: String? = null,
    override val singleLineTitle: Boolean = true,
    override val icon: (@Composable () -> Unit)? = null,
    override val enabled: Boolean = true,
    val items: List<PreferenceItem>,
) : PreferenceItem

public object PreferenceDivider : PreferenceItem {
    override val title: String = ""
    override val summary: String? = null
    override val singleLineTitle: Boolean = true
    override val icon: (@Composable () -> Unit)? = null
    override val enabled: Boolean = true
}
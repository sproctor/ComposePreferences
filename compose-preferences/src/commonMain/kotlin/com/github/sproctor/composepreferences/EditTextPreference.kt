package com.github.sproctor.composepreferences

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
public fun EditTextPreference(
    title: String,
    summary: String? = null,
    value: String?,
    onValueChanged: (String) -> Unit = {},
    singleLineTitle: Boolean = true,
    icon: ImageVector? = null,
    enabled: Boolean = true,
    isPassword: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    dismissText: String = "CANCEL",
    confirmText: String = "OK",
) {
    val showDialog = remember { mutableStateOf(false) }
    val closeDialog = { showDialog.value = false }

    val text = if (isPassword) "(hidden)" else value
    Preference(
        title = title,
        summary = text ?: summary,
        singleLineTitle = singleLineTitle,
        icon = icon,
        enabled = enabled,
        onClick = { showDialog.value = true },
    )

    if (showDialog.value) {
        EditTextDialog(
            title = title,
            value = value ?: "",
            isPassword = isPassword,
            keyboardOptions = keyboardOptions,
            onDismissRequest = closeDialog,
            onConfirm = onValueChanged,
            dismissText = dismissText,
            confirmText = confirmText
        )
    }
}

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
private fun EditTextDialog(
    title: String,
    value: String,
    isPassword: Boolean,
    keyboardOptions: KeyboardOptions,
    onDismissRequest: () -> Unit,
    onConfirm: (String) -> Unit,
    dismissText: String,
    confirmText: String
) {
    var textValue: TextFieldValue by remember {
        mutableStateOf(TextFieldValue(text = value, selection = TextRange(value.length)))
    }
    PreferenceDialog(
        onDismissRequest = onDismissRequest,
        title = title,
        onConfirm = {
            onConfirm(textValue.text)
            onDismissRequest()
        },
        dismissText = dismissText,
        confirmText = confirmText
    ) {
        val useKeyboardOptions = when {
            keyboardOptions != KeyboardOptions.Default -> keyboardOptions
            isPassword -> KeyboardOptions(keyboardType = KeyboardType.Password)
            else -> KeyboardOptions.Default
        }
        val focusRequester = FocusRequester()
        var transformPassword by remember(isPassword) { mutableStateOf(isPassword) }
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            value = textValue,
            onValueChange = { textValue = it },
            singleLine = true,
            visualTransformation = if (transformPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = useKeyboardOptions,
            trailingIcon = {
                if (isPassword) {
                    IconButton(
                        onClick = { transformPassword = !transformPassword }
                    ) {
                        Image(
                            imageVector = if (transformPassword) {
                                Icons.Default.Visibility
                            } else {
                                Icons.Default.VisibilityOff
                            },
                            contentDescription = "Toggle password visibility"
                        )
                    }
                }
            }
        )
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}
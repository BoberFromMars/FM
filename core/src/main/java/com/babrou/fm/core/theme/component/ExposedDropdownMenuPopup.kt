package com.babrou.fm.core.theme.component

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.InputModeManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalInputModeManager
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import androidx.compose.ui.window.PopupProperties

@SuppressLint("UnrememberedMutableState")
@Composable
internal fun ExposedDropdownMenuPopup(
    onDismissRequest: (() -> Unit)?,
    popupPositionProvider: PopupPositionProvider,
    content: @Composable () -> Unit
) {
    var focusManager: FocusManager? by mutableStateOf(null)
    var inputModeManager: InputModeManager? by mutableStateOf(null)
    Popup(
        popupPositionProvider = popupPositionProvider,
        onDismissRequest = onDismissRequest,
        properties = PopupProperties(focusable = true),
    ) {
        focusManager = LocalFocusManager.current
        inputModeManager = LocalInputModeManager.current
        content()
    }
}
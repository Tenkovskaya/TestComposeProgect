package com.tenkovskaya.testcomposeprogect

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class InfoDialog {
    companion object {
        @Composable
        fun Show(
            showDialog: Boolean,
            onClose: () -> Unit,
            title: String,
            message: String
        ) {
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = onClose,
                    title = { Text(text = title) },
                    text = { Text(message) },
                    confirmButton = {
                        Button(onClick = onClose) {
                            Text("OK")
                        }
                    }
                )
            }
        }
    }
}


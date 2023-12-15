package com.maxidev.stoic.ui.components

import androidx.annotation.StringRes
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    @StringRes buttonName: Int,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        elevation = ButtonDefaults.buttonElevation(6.dp),
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = buttonName),
            style = MaterialTheme.typography.bodySmall
        )
    }
}
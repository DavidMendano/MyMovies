package com.dmendanyo.mymovies.ui.common

import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import com.dmendanyo.mymovies.R


@Composable
fun RequestLocationPermission(
    permission: String = locationPermission,
    onPermissionRequested: () -> Unit,
) {
    val context = LocalContext.current
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { onPermissionRequested() }

    LaunchedEffect(Unit) {
        if (
            ContextCompat.checkSelfPermission(
                context, permission
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            onPermissionRequested()
        } else {
            permissionLauncher.launch(permission)
        }
    }
}

@Composable
private fun ShowRationale(onAccepted: () -> Unit) {
    ShowAlertDialog(
        R.string.permission_denied_dialog_title,
        R.string.permission_denied_dialog_description,
        R.string.permission_denied_dialog_accept_button,
    ) { onAccepted() }
}

@Composable
private fun ShowAlertDialog(
    @StringRes title: Int,
    @StringRes description: Int,
    @StringRes ctaText: Int = R.string.accept,
    ctaAction: () -> Unit = {},
) {
    val show = remember { mutableStateOf(true) }

    if (show.value) {
        AlertDialog(
            onDismissRequest = { show.value = false },
            title = {
                Title(modifier = Modifier, text = stringResource(id = title))
            },
            text = {
                Subtitle(
                    modifier = Modifier,
                    text = stringResource(id = description),
                    maxLines = 5,
                )
            },
            confirmButton = {
                Button(onClick = {
                    show.value = false
                    ctaAction()
                }
                ) {
                    Text(text = stringResource(id = ctaText))
                }
            },
        )
    }
}

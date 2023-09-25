package com.dmendanyo.mymovies.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dmendanyo.mymovies.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErrorBottomSheet(
    title: String,
    subtitle: String,
    ctaText: String = stringResource(R.string.accept),
    onAccept: () -> Unit = {},
    onDismiss: () -> Unit = {},
) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        modifier = Modifier.padding(bottom = 32.dp),
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 64.dp)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Title(Modifier, title)
            Subtitle(Modifier, subtitle)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onAccept,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(ctaText)
            }
        }
    }

}
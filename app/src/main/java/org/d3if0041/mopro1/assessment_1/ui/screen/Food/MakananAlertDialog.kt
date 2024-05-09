package org.d3if0041.mopro1.assessment_1.ui.screen.Food

import android.content.res.Configuration
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.d3if0041.mopro1.assessment_1.R
import org.d3if0041.mopro1.assessment_1.ui.theme.Assessment_1Theme

@Composable
fun MakananAlertDialog(
    openDialog: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    if (openDialog) {
        AlertDialog(
            text = { Text(text = stringResource(R.string.tombol_hapus)) },
            confirmButton = {
                TextButton(onClick = {onConfirmation() }) {
                    Text(text = stringResource(id = R.string.tombol_hapus))

                }
            },
            dismissButton = {
                TextButton(onClick = { onDismissRequest() }) {
                    Text(text = stringResource(R.string.tombol_batal))
                }
            },
            onDismissRequest = {onDismissRequest()}
        )
    }
}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DialogPreview() {
    Assessment_1Theme {
        MakananAlertDialog(
            openDialog = true,
            onDismissRequest = {},
            onConfirmation = {}
        )
    }
}

package com.ferhatminder.notes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ferhatminder.notes.domain.model.Note
import com.ferhatminder.notes.ui.theme.NotesTheme
import com.ferhatminder.notes.ui.theme.Typography

@Composable
fun NoteEditScreen() {
    val note = Note(
        1,
        "Todo",
        "- Buy some milk, eggs\n- Clean room\n- Cook dinner"
    )

    NoteEditScreenContent(
        note = note,
        onChangeTitle = {},
        onChangeBody = {},
        onClickBack = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NoteEditScreenContent(
    note: Note,
    onChangeTitle: (title: String) -> Unit = {},
    onChangeBody: (body: String) -> Unit = {},
    onClickBack: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                NoteTitle(
                    title = note.title,
                    onChangeTitle = onChangeTitle,
                    onClickBack = onClickBack,
                )
            }
        }
    ) {
        Column(Modifier.padding(it)) {
            NoteBody(
                body = note.body,
                onChangeBody = onChangeBody
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NoteTitle(
    title: String,
    onChangeTitle: (title: String) -> Unit = {},
    onClickBack: () -> Unit = {}
) {
    TextField(
        value = title,
        onValueChange = onChangeTitle,
        modifier = Modifier
            .fillMaxWidth()

            .padding(vertical = 8.dp),
        textStyle = Typography.titleLarge,
        singleLine = true,
        leadingIcon = {
            IconButton(
                onClick = onClickBack
            ) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_revert),
                    contentDescription = "Go Back"
                )
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        shape = TextFieldDefaults.outlinedShape,
        placeholder = {
            Text(
                text = "Title",
                fontStyle = Typography.titleLarge.fontStyle
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NoteBody(
    body: String,
    onChangeBody: (title: String) -> Unit = {},
) {
    TextField(
        value = body,
        onValueChange = onChangeBody,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
            .fillMaxSize(),
        textStyle = Typography.bodyMedium,
        singleLine = false,
        colors = TextFieldDefaults.textFieldColors(
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        shape = TextFieldDefaults.outlinedShape,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun PreviewNoteListScreenContent() {
    NotesTheme {
        Column {
            NoteEditScreenContent(
                note = Note(
                    1,
                    "Todo",
                    "- Buy some milk, eggs\n- Clean room\n- Cook dinner"
                )
            )
        }

    }
}
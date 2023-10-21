package com.ferhatminder.notes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ferhatminder.notes.domain.model.Note
import com.ferhatminder.notes.ui.theme.NotesTheme
import com.ferhatminder.notes.ui.theme.Typography

@Composable
fun NoteListScreen(
    notes: List<Note>,
    onClick: (note: Note) -> Unit = {},
    onClickAddButton: () -> Unit = {},
) {
    NoteListScreenContent(
        notes = notes,
        onClick = onClick,
        onClickAddButton = onClickAddButton
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NoteListScreenContent(
    notes: List<Note>,
    onClick: (note: Note) -> Unit = {},
    onClickAddButton: () -> Unit = {},
) {
    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onClickAddButton() },
                shape = FloatingActionButtonDefaults.largeShape,
            ) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_edit),
                    contentDescription = "Add a note",
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            NoteListTitle()
            notes.forEach { note ->
                NoteListItem(
                    note,
                    onClick = {
                        onClick(note)
                    }
                )
            }
        }
    }
}

@Composable
private fun NoteListTitle() {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .padding(bottom = 4.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inverseSurface,
            contentColor = MaterialTheme.colorScheme.inverseOnSurface,
        ),
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            text = "Notes",
            fontStyle = Typography.titleLarge.fontStyle,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun NoteListItem(
    note: Note,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(vertical = 4.dp)
            .fillMaxWidth()
            .clickable(role = Role.Button) {
                onClick()
            },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(vertical = 8.dp),

            ) {
            Text(
                text = note.title,
                fontStyle = Typography.titleMedium.fontStyle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = note.body,
                fontStyle = Typography.bodyMedium.fontStyle,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Preview
@Composable
private fun PreviewNoteListScreenContent() {
    NotesTheme {

        NoteListScreenContent(
            notes = listOf(
                Note(
                    1,
                    "Todo",
                    "- Buy some milk, eggs\n- Clean room\n- Cook dinner"
                ),
                Note(
                    2,
                    "Quotes",
                    "Around the survivors a perimeter create.\n... a mind needs books as a sword needs a whetstone, if it is to keep its edge."
                ),
                Note(
                    3,
                    "Cites",
                    "Port Jarod, Raulbury, New Darrenville"
                ),
                Note(
                    4,
                    "Countries",
                    "French Guiana, Myanmar, Grenada"
                )
            ),
        )

    }
}
package com.ferhatminder.notes

import androidx.lifecycle.ViewModel
import com.ferhatminder.notes.domain.model.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NotesViewModel : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    val notes = mutableListOf(
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
    )

    fun sendIntent(intent: Intent) {
        when (intent) {
            Intent.GetNotes -> getNotes()

            is Intent.EditNote -> selectEditNode(intent)

            is Intent.ChangeTitle -> changeTitle(intent)

            is Intent.ChangeBody -> changeBody(intent)
        }
    }

    private fun getNotes() {
        _state.update { it.copy(notes = notes) }
    }

    private fun selectEditNode(intent: Intent.EditNote) {
        val index = _state.value.notes.indexOfFirst { it.id == intent.id }
        if (index == -1) {
            _state.update {
                it.copy(
                    editNote = Note()
                )
            }
        } else {
            _state.update {
                it.copy(
                    editNote = notes[index]
                )
            }
        }
    }

    private fun updateInsertNote(note: Note): Note {
        var upsertedNote: Note = note
        val index = notes.indexOfFirst { it.id == note.id }
        if (index == -1) {
            upsertedNote = note.copy(
                id = notes.size
            )
            notes.add(0, upsertedNote)
        } else {
            notes.removeAt(index)
            notes.add(0, note)
        }

        return upsertedNote
    }

    private fun changeTitle(intent: Intent.ChangeTitle) {
        _state.update {
            it.copy(
                editNote = updateInsertNote(
                    _state.value.editNote.copy(
                        title = intent.title
                    )
                )
            )
        }
    }

    private fun changeBody(intent: Intent.ChangeBody) {
        _state.update {
            it.copy(
                editNote = updateInsertNote(
                    _state.value.editNote.copy(
                        body = intent.body
                    )
                )
            )
        }
    }

    sealed class Intent {
        object GetNotes : Intent()
        data class EditNote(val id: Int?) : Intent()
        data class ChangeTitle(val title: String) : Intent()
        data class ChangeBody(val body: String) : Intent()
    }

    data class State(
        val notes: List<Note> = emptyList(),
        val editNote: Note = Note(),
    )
}

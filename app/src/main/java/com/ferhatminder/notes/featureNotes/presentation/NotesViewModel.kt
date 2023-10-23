package com.ferhatminder.notes.featureNotes.presentation

import androidx.lifecycle.ViewModel
import com.ferhatminder.notes.featureNotes.domain.model.Note
import com.ferhatminder.notes.featureNotes.domain.repository.NotesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NotesViewModel(
    private val repository: NotesRepository
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    fun sendIntent(intent: Intent) {
        when (intent) {
            Intent.GetNotes -> getNotes()

            is Intent.EditNote -> selectEditNode(intent)

            is Intent.ChangeTitle -> changeTitle(intent)

            is Intent.ChangeBody -> changeBody(intent)
        }
    }

    private fun getNotes() {
        val notes = repository.getNotes()
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
                    editNote = _state.value.notes[index]
                )
            }
        }
    }


    private fun changeTitle(intent: Intent.ChangeTitle) {
        val editedNote = repository.updateInsertNote(
            _state.value.editNote.copy(
                title = intent.title
            )
        )
        _state.update {
            it.copy(
                editNote = editedNote
            )
        }
    }

    private fun changeBody(intent: Intent.ChangeBody) {
        val editedNote = repository.updateInsertNote(
            _state.value.editNote.copy(
                body = intent.body
            )
        )
        _state.update {
            it.copy(
                editNote = editedNote
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

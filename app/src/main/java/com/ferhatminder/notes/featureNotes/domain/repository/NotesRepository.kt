package com.ferhatminder.notes.featureNotes.domain.repository

import com.ferhatminder.notes.featureNotes.domain.model.Note

interface NotesRepository {
    fun getNotes(): List<Note>
    fun updateInsertNote(note: Note): Note
}
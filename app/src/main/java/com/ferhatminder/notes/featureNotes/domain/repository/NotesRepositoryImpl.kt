package com.ferhatminder.notes.featureNotes.domain.repository

import com.ferhatminder.notes.featureNotes.domain.model.Note

class NotesRepositoryImpl : NotesRepository {
    private val notes = mutableListOf(
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

    override fun getNotes(): List<Note> {
        return notes
    }

    override fun updateInsertNote(note: Note): Note {
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
}
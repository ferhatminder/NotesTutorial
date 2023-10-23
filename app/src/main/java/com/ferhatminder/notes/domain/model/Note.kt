package com.ferhatminder.notes.domain.model

/**
 * Classes that holds data and generates some useful functions
 * @see <a href="https://kotlinlang.org/docs/data-classes.html">Data Class Documentation</a>
 */
data class Note(
    val id: Int = 0,
    val title: String = "",
    val body: String = ""
)

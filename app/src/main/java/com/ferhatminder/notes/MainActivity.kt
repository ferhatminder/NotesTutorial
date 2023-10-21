package com.ferhatminder.notes

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ferhatminder.notes.domain.model.Note
import com.ferhatminder.notes.ui.theme.NotesTheme

class MainActivity : ComponentActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesTheme {
                val notes = remember {
                    mutableListOf(
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
                }

                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "todos"
                ) {
                    composable(route = "todos") {
                        NoteListScreen(
                            notes,
                            onClick = {
                                navController.navigate("todos/${it.id}")
                            },
                            onClickAddButton = {
                                navController.navigate("todos/${0}")
                            }
                        )
                    }

                    composable(route = "todos/{id}", arguments = listOf(
                        navArgument("id") {
                            this.type = NavType.IntType
                            this.defaultValue = 0
                            this.nullable = false
                        }
                    )) { entry ->
                        val id = entry.arguments?.getInt("id")
                        var updatedNote: Note by remember(id) {
                            mutableStateOf(
                                notes.firstOrNull { it.id == id }
                                    ?: Note(notes.size, "", "")
                            )
                        }
                        if (id != 0) {
                            NoteEditScreen(
                                note = updatedNote,
                                onClickBack = {
                                    val note = notes.first { it.id == id }
                                    if (updatedNote != note) {
                                        notes.remove(note)
                                        notes.add(0, updatedNote)
                                    }
                                    navController.popBackStack()
                                },
                                onChangeTitle = {
                                    updatedNote = updatedNote.copy(
                                        title = it
                                    )
                                },
                                onChangeBody = {
                                    updatedNote = updatedNote.copy(
                                        body = it
                                    )
                                    Log.d(TAG, "onChangeBody: Input: $it")
                                    Log.d(TAG, "onChangeBody: Note: $updatedNote")
                                },
                            )
                        } else {
                            NoteEditScreen(
                                note = updatedNote,
                                onClickBack = {
                                    notes.add(0, updatedNote)
                                    navController.popBackStack()
                                },
                                onChangeTitle = {
                                    updatedNote = updatedNote.copy(
                                        title = it
                                    )
                                },
                                onChangeBody = {
                                    updatedNote = updatedNote.copy(
                                        body = it
                                    )
                                    Log.d(TAG, "onChangeBody: Input: $it")
                                    Log.d(TAG, "onChangeBody: Note: $updatedNote")
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}
package com.ferhatminder.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ferhatminder.notes.featureNotes.domain.repository.NotesRepositoryImpl
import com.ferhatminder.notes.featureNotes.presentation.NotesViewModel
import com.ferhatminder.notes.featureNotes.presentation.screen.NoteEditScreen
import com.ferhatminder.notes.featureNotes.presentation.screen.NoteListScreen
import com.ferhatminder.notes.ui.theme.NotesTheme

class MainActivity : ComponentActivity() {
    companion object {
        private const val TAG = "MainActivity"
        private const val ANIM_DURATION = 400
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesTheme {
                Surface {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "todos"
                    ) {
                        composable(
                            route = "todos",
                            enterTransition = {
                                slideInHorizontally(
                                    animationSpec = tween(ANIM_DURATION),
                                    initialOffsetX = { -it }
                                )
                            },
                            exitTransition = {
                                slideOutHorizontally(
                                    animationSpec = tween(ANIM_DURATION),
                                    targetOffsetX = { -it }
                                )
                            }
                        ) {
                            val viewModel = viewModel<NotesViewModel>(
                                viewModelStoreOwner = it,
                                factory = object : ViewModelProvider.Factory {
                                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                        @Suppress("UNCHECKED_CAST")
                                        return NotesViewModel(
                                            NotesRepositoryImpl()
                                        ) as T
                                    }
                                }
                            )

                            val state by viewModel.state.collectAsState()

                            LaunchedEffect(Unit) {
                                viewModel.sendIntent(NotesViewModel.Intent.GetNotes)
                            }

                            NoteListScreen(
                                state.notes,
                                onClick = { note ->
                                    navController.navigate("todos/${note.id}")
                                },
                                onClickAddButton = {
                                    navController.navigate("todos/${0}")
                                }
                            )
                        }

                        composable(
                            route = "todos/{id}",
                            arguments = listOf(
                                navArgument("id") {
                                    type = NavType.IntType
                                    defaultValue = 0
                                    nullable = false
                                }
                            ),
                            enterTransition = {
                                slideInHorizontally(
                                    animationSpec = tween(ANIM_DURATION),
                                    initialOffsetX = { it }
                                )
                            },
                            exitTransition = {
                                slideOutHorizontally(
                                    animationSpec = tween(ANIM_DURATION),
                                    targetOffsetX = { it }
                                )
                            }
                        ) { entry ->
                            val id = entry.arguments?.getInt("id")
                            val previousEntry = remember(entry) {
                                navController.getBackStackEntry("todos")
                            }
                            val viewModel = viewModel<NotesViewModel>(
                                viewModelStoreOwner = previousEntry,
                                factory = object : ViewModelProvider.Factory {
                                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                        @Suppress("UNCHECKED_CAST")
                                        return NotesViewModel(
                                            NotesRepositoryImpl()
                                        ) as T
                                    }
                                }
                            )

                            val state by viewModel.state.collectAsState()

                            LaunchedEffect(Unit) {
                                viewModel.sendIntent(NotesViewModel.Intent.EditNote(id))
                            }

                            NoteEditScreen(
                                note = state.editNote,
                                onClickBack = {
                                    navController.popBackStack()
                                },
                                onChangeTitle = {
                                    viewModel.sendIntent(
                                        NotesViewModel.Intent.ChangeTitle(it)
                                    )
                                },
                                onChangeBody = {
                                    viewModel.sendIntent(
                                        NotesViewModel.Intent.ChangeBody(it)
                                    )
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}
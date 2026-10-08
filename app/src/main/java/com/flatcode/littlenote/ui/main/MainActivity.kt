package com.flatcode.littlenote.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.flatcode.littlenote.ui.auth.ForgetPasswordScreen
import com.flatcode.littlenote.ui.auth.LoginScreen
import com.flatcode.littlenote.ui.auth.RegisterScreen
import com.flatcode.littlenote.ui.home.HomeScreen
import com.flatcode.littlenote.ui.note.AddNoteScreen
import com.flatcode.littlenote.ui.note.EditNoteScreen
import com.flatcode.littlenote.ui.note.NoteDetailsScreen
import com.flatcode.littlenote.ui.theme.LittleNoteTheme
import com.flatcode.littlenote.viewmodel.AuthViewModel
import com.flatcode.littlenote.viewmodel.HomeViewModel
import com.flatcode.littlenote.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels()
    private val authViewModel: AuthViewModel by viewModels()
    private val noteViewModel: NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            LittleNoteTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LittleNoteNavHost(
                        homeViewModel = homeViewModel,
                        authViewModel = authViewModel,
                        noteViewModel = noteViewModel,
                        onExitApp = { finish() },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        noteViewModel.syncNotes()
    }
}

object Routes {
    const val HOME = "home"
    const val ADD_NOTE = "add_note"
    const val EDIT_NOTE = "edit_note/{noteId}"
    const val NOTE_DETAILS = "note_details/{noteId}/{color}"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val FORGET_PASSWORD = "forget_password"

    fun editNoteRoute(noteId: Int) = "edit_note/$noteId"
    fun noteDetailsRoute(noteId: Int, color: Color) = "note_details/$noteId/${color.toArgb()}"
}

@Composable
fun LittleNoteNavHost(
    homeViewModel: HomeViewModel,
    authViewModel: AuthViewModel,
    noteViewModel: NoteViewModel,
    onExitApp: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val allNotes by noteViewModel.allNotes.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME,
        modifier = modifier,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                homeViewModel = homeViewModel,
                authViewModel = authViewModel,
                noteViewModel = noteViewModel,
                onNavigateToAddNote = { navController.navigate(Routes.ADD_NOTE) },
                onNavigateToEditNote = { note -> navController.navigate(Routes.editNoteRoute(note.id)) },
                onNavigateToNoteDetails = { note, color -> navController.navigate(Routes.noteDetailsRoute(note.id, color)) },
                onNavigateToLogin = { navController.navigate(Routes.LOGIN) },
                onNavigateToRegister = { navController.navigate(Routes.REGISTER) },
                onExitApp = onExitApp
            )
        }

        composable(Routes.ADD_NOTE) {
            AddNoteScreen(
                noteViewModel = noteViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Routes.EDIT_NOTE,
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId") ?: -1
            val note = allNotes.find { it.id == noteId }
            if (note != null) {
                EditNoteScreen(
                    note = note,
                    noteViewModel = noteViewModel,
                    onBack = { navController.popBackStack() }
                )
            }
        }

        composable(
            route = Routes.NOTE_DETAILS,
            arguments = listOf(
                navArgument("noteId") { type = NavType.IntType },
                navArgument("color") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId") ?: -1
            val colorInt = backStackEntry.arguments?.getInt("color") ?: 0
            val note = allNotes.find { it.id == noteId }
            if (note != null) {
                NoteDetailsScreen(
                    note = note,
                    color = Color(colorInt),
                    noteViewModel = noteViewModel,
                    onBack = { navController.popBackStack() },
                    onEditNote = { selectedNote ->
                        navController.navigate(Routes.editNoteRoute(selectedNote.id))
                    }
                )
            }
        }

        composable(Routes.LOGIN) {
            LoginScreen(
                authViewModel = authViewModel,
                onNavigateToHome = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.HOME) { inclusive = true }
                    }
                },
                onNavigateToRegister = { navController.navigate(Routes.REGISTER) },
                onNavigateToForgetPassword = { navController.navigate(Routes.FORGET_PASSWORD) }
            )
        }

        composable(Routes.REGISTER) {
            RegisterScreen(
                authViewModel = authViewModel,
                onNavigateToHome = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.HOME) { inclusive = true }
                    }
                },
                onNavigateToLogin = { navController.navigate(Routes.LOGIN) }
            )
        }

        composable(Routes.FORGET_PASSWORD) {
            ForgetPasswordScreen(
                authViewModel = authViewModel,
                onNavigateToLogin = { navController.navigate(Routes.LOGIN) },
                onNavigateToRegister = { navController.navigate(Routes.REGISTER) }
            )
        }
    }
}
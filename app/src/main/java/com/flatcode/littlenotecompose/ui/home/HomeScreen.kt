package com.flatcode.littlenotecompose.ui.home

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.flatcode.littlenotecompose.data.model.Note
import com.flatcode.littlenotecompose.ui.components.AboutAccountDialog
import com.flatcode.littlenotecompose.ui.components.AnonymousLogoutWarningDialog
import com.flatcode.littlenotecompose.ui.components.CloseAppDialog
import com.flatcode.littlenotecompose.ui.components.ToolbarMain
import com.flatcode.littlenotecompose.ui.note.ItemNote
import com.flatcode.littlenotecompose.ui.theme.Strings
import com.flatcode.littlenotecompose.utils.DATA
import com.flatcode.littlenotecompose.viewmodel.AuthViewModel
import com.flatcode.littlenotecompose.viewmodel.HomeViewModel
import com.flatcode.littlenotecompose.viewmodel.NoteViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    authViewModel: AuthViewModel,
    noteViewModel: NoteViewModel,
    onNavigateToAddNote: () -> Unit,
    onNavigateToEditNote: (Note) -> Unit,
    onNavigateToNoteDetails: (Note, Color) -> Unit,
    onNavigateToLogin: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onExitApp: () -> Unit
) {
    val context = LocalContext.current
    val notes by noteViewModel.allNotes.collectAsState()
    val currentUser = homeViewModel.currentUser
    val isAnonymous = currentUser?.isAnonymous == true

    var showExitDialog by remember { mutableStateOf(false) }
    var showAccountInfoDialog by remember { mutableStateOf(false) }
    var showAnonymousLogoutDialog by remember { mutableStateOf(false) }

    BackHandler {
        showExitDialog = true
    }

    if (showExitDialog) {
        CloseAppDialog(
            onConfirm = {
                showExitDialog = false
                onExitApp()
            },
            onDismiss = { showExitDialog = false }
        )
    }

    if (showAccountInfoDialog) {
        AboutAccountDialog(
            username = currentUser?.displayName,
            email = currentUser?.email,
            onDismiss = { showAccountInfoDialog = false }
        )
    }

    if (showAnonymousLogoutDialog) {
        AnonymousLogoutWarningDialog(
            onSyncNotes = {
                showAnonymousLogoutDialog = false
                onNavigateToRegister()
            },
            onLogout = {
                showAnonymousLogoutDialog = false
                authViewModel.deleteAnonymousUser(currentUser?.uid ?: "")
            },
            onDismiss = { showAnonymousLogoutDialog = false }
        )
    }

    Scaffold(
        containerColor = DATA.COLOR_ON_BACKGROUND,
        topBar = {
            ToolbarMain(
                notesCount = notes.size,
                isAnonymous = isAnonymous,
                onAddClick = onNavigateToAddNote,
                onInfoClick = { showAccountInfoDialog = true },
                onSyncClick = onNavigateToLogin,
                onLogoutClick = {
                    if (isAnonymous) {
                        showAnonymousLogoutDialog = true
                    } else {
                        authViewModel.signOut()
                        onNavigateToLogin()
                    }
                }
            )
        }
    ) { paddingValues ->
        if (notes.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(text = Strings.HINT_DESCRIPTION)
            }
        } else {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(4.dp)
            ) {
                items(notes, key = { it.id }) { note ->
                    ItemNote(
                        note = note,
                        onClick = { selectedNote, color ->
                            onNavigateToNoteDetails(selectedNote, color)
                        },
                        onEdit = { selectedNote ->
                            onNavigateToEditNote(selectedNote)
                        },
                        onDelete = { selectedNote ->
                            noteViewModel.deleteNote(selectedNote)
                            Toast.makeText(context, "Note Deleted", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}
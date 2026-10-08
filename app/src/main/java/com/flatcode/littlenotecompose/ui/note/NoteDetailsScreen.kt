package com.flatcode.littlenotecompose.ui.note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flatcode.littlenotecompose.data.model.Note
import com.flatcode.littlenotecompose.ui.components.ToolbarAddEdit
import com.flatcode.littlenotecompose.ui.theme.AppIcons
import com.flatcode.littlenotecompose.ui.theme.Dark
import com.flatcode.littlenotecompose.utils.DATA
import com.flatcode.littlenotecompose.viewmodel.NoteViewModel

@Composable
fun NoteDetailsScreen(
    note: Note,
    color: Color,
    noteViewModel: NoteViewModel,
    onBack: () -> Unit,
    onEditNote: (Note) -> Unit
) {
    val allNotes by noteViewModel.allNotes.collectAsState()
    val currentNote = allNotes.find { it.id == note.id } ?: note

    Scaffold(
        containerColor = DATA.COLOR_ON_BACKGROUND,
        topBar = {
            ToolbarAddEdit(
                title = currentNote.title ?: "",
                onBackClick = onBack,
                actionIcon = AppIcons.Edit,
                onActionClick = { onEditNote(currentNote) }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = currentNote.content ?: "",
                fontSize = 16.sp,
                color = Dark,
                lineHeight = 24.sp
            )
        }
    }
}
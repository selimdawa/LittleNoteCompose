package com.flatcode.littlenotecompose.ui.note

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.flatcode.littlenotecompose.data.model.Note
import com.flatcode.littlenotecompose.ui.components.ToolbarAddEdit
import com.flatcode.littlenotecompose.ui.theme.AppIcons
import com.flatcode.littlenotecompose.ui.theme.Dimen
import com.flatcode.littlenotecompose.ui.theme.Strings
import com.flatcode.littlenotecompose.ui.theme.White
import com.flatcode.littlenotecompose.utils.DATA
import com.flatcode.littlenotecompose.utils.DATA.COLOR_ERROR
import com.flatcode.littlenotecompose.utils.DATA.MC_BG
import com.flatcode.littlenotecompose.viewmodel.NoteViewModel

@Composable
fun EditNoteScreen(
    note: Note, noteViewModel: NoteViewModel, onBack: () -> Unit
) {
    val context = LocalContext.current
    var title by remember { mutableStateOf(note.title ?: "") }
    var content by remember { mutableStateOf(note.content ?: "") }

    Scaffold(
        containerColor = DATA.COLOR_ON_BACKGROUND,
        topBar = {
            ToolbarAddEdit(
                title = Strings.EDIT_NOTE,
                onBackClick = onBack,
                actionIcon = AppIcons.Check,
                onActionClick = {
                    if (title.isBlank() || content.isBlank()) {
                        Toast.makeText(context, Strings.ERROR_EMPTY, Toast.LENGTH_SHORT).show()
                    } else {
                        val updatedNote = note.copy(title = title, content = content)
                        noteViewModel.editNote(updatedNote)
                        onBack()
                    }
                })
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = Dimen.SPACING_10)
        ) {
            Card(
                shape = RoundedCornerShape(Dimen.CARD_CORNER_RADIUS_SMALL),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MC_BG)
                        .padding(Dimen.SPACING_10)
                ) {
                    if (title.isEmpty()) {
                        Text(
                            text = Strings.TITLE_HERE,
                            color = White.copy(alpha = 0.7f),
                            fontSize = Dimen.TEXT_SIZE_21,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    BasicTextField(
                        value = title, onValueChange = { title = it }, textStyle = TextStyle(
                            color = White,
                            fontSize = Dimen.TEXT_SIZE_21,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        ), singleLine = true, modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(Dimen.SPACING_10))

            Card(
                shape = RoundedCornerShape(Dimen.CARD_CORNER_RADIUS_MEDIUM),
                colors = CardDefaults.cardColors(containerColor = DATA.COLOR_ON_BACKGROUND),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(vertical = Dimen.SPACING_10)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(Dimen.SPACING_10)
                ) {
                    if (content.isEmpty()) {
                        Text(
                            text = Strings.HINT_DESCRIPTION,
                            color = COLOR_ERROR.copy(alpha = 0.6f),
                            fontSize = Dimen.TEXT_SIZE_16,
                            modifier = Modifier.align(Alignment.TopStart)
                        )
                    }
                    BasicTextField(
                        value = content, onValueChange = { content = it }, textStyle = TextStyle(
                            color = COLOR_ERROR, fontSize = Dimen.TEXT_SIZE_16
                        ), modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}
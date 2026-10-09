package com.flatcode.littlenotecompose.ui.note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.flatcode.littlenotecompose.data.model.Note
import com.flatcode.littlenotecompose.ui.theme.AppIcons
import com.flatcode.littlenotecompose.ui.theme.Dark
import com.flatcode.littlenotecompose.ui.theme.Dimen
import com.flatcode.littlenotecompose.ui.theme.OverlayDark20
import com.flatcode.littlenotecompose.ui.theme.White
import com.flatcode.littlenotecompose.utils.DATA
import com.flatcode.littlenotecompose.utils.noRippleClickable

@Composable
fun ItemNote(
    note: Note,
    onClick: (Note, Color) -> Unit,
    onEdit: (Note) -> Unit,
    onDelete: (Note) -> Unit,
    modifier: Modifier = Modifier,
    cardColor: Color = remember(note.id) { DATA.randomColor }
) {
    var showMenu by remember { mutableStateOf(false) }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = Dimen.SPACING_5)
                .padding(bottom = Dimen.SPACING_10)
                .noRippleClickable { onClick(note, cardColor) },
            shape = RoundedCornerShape(Dimen.CARD_CORNER_RADIUS_SMALL),
            colors = CardDefaults.cardColors(containerColor = cardColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(OverlayDark20)
                        .padding(Dimen.SPACING_10)
                ) {
                    Text(
                        text = note.title ?: "",
                        modifier = Modifier.fillMaxWidth(),
                        color = White,
                        fontSize = Dimen.TEXT_SIZE_18,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        fontFamily = FontFamily.SansSerif,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimen.SPACING_10),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                        Text(
                            text = note.content ?: "",
                            modifier = Modifier.weight(1f),
                            color = Dark,
                            fontSize = Dimen.TEXT_SIZE_14,
                            fontWeight = FontWeight.Bold,
                            maxLines = 6,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Spacer(modifier = Modifier.width(Dimen.SPACING_5))

                    Box {
                        Icon(
                            imageVector = AppIcons.MoreVert,
                            contentDescription = "More Options",
                            tint = Dark,
                            modifier = Modifier
                                .size(20.dp)
                                .noRippleClickable { showMenu = true }
                        )

                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false },
                            modifier = Modifier.background(DATA.COLOR_ON_BACKGROUND)
                        ) {
                            DropdownMenuItem(text = {
                                Text(
                                    text = DATA.EDIT,
                                    color = DATA.COLOR_ERROR,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }, onClick = {
                                showMenu = false
                                onEdit(note)
                            })
                            DropdownMenuItem(text = {
                                Text(
                                    text = DATA.DELETE,
                                    color = DATA.COLOR_ERROR,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }, onClick = {
                                showMenu = false
                                onDelete(note)
                            })
                        }
                    }
                }
            }
        }
    }
}
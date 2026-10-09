package com.flatcode.littlenotecompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.flatcode.littlenotecompose.ui.theme.AppIcons
import com.flatcode.littlenotecompose.ui.theme.Dimen
import com.flatcode.littlenotecompose.ui.theme.Strings
import com.flatcode.littlenotecompose.ui.theme.White
import com.flatcode.littlenotecompose.utils.DATA.MC_BG
import io.selimdawa.multicolors.MultiColorButton

@Composable
fun ToolbarMain(
    notesCount: Int,
    isAnonymous: Boolean,
    onAddClick: () -> Unit,
    onInfoClick: () -> Unit,
    onSyncClick: () -> Unit,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimen.TOOLBAR_HEIGHT)
            .padding(horizontal = Dimen.SPACING_10, vertical = Dimen.SPACING_5),
        shape = RoundedCornerShape(Dimen.CARD_CORNER_RADIUS_SMALL),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(MC_BG)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = Dimen.SPACING_10),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AndroidView(
                    factory = { ctx -> MultiColorButton(ctx) },
                    modifier = Modifier.size(Dimen.SPACING_30)
                )

                Spacer(modifier = Modifier.width(Dimen.SPACING_10))

                IconButton(
                    onClick = onAddClick,
                    modifier = Modifier.size(Dimen.SPACING_30)
                ) {
                    Icon(
                        imageVector = AppIcons.AddCircle,
                        contentDescription = Strings.CD_ADD,
                        tint = White,
                        modifier = Modifier.size(Dimen.SPACING_30)
                    )
                }

                Text(
                    text = "${Strings.MY_NOTES} ($notesCount)",
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 4.dp),
                    color = White,
                    fontSize = Dimen.TEXT_SIZE_21,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                IconButton(
                    onClick = onLogoutClick,
                    modifier = Modifier.size(Dimen.SPACING_30)
                ) {
                    Icon(
                        imageVector = AppIcons.Power,
                        contentDescription = Strings.CD_LOGOUT,
                        tint = White,
                        modifier = Modifier.size(Dimen.SPACING_30)
                    )
                }

                Spacer(modifier = Modifier.width(Dimen.SPACING_8))

                if (isAnonymous) {
                    IconButton(
                        onClick = onSyncClick,
                        modifier = Modifier.size(Dimen.SPACING_30)
                    ) {
                        Icon(
                            imageVector = AppIcons.Sync,
                            contentDescription = Strings.CD_SYNC,
                            tint = White,
                            modifier = Modifier.size(Dimen.SPACING_30)
                        )
                    }
                } else {
                    IconButton(
                        onClick = onInfoClick,
                        modifier = Modifier.size(Dimen.SPACING_30)
                    ) {
                        Icon(
                            imageVector = AppIcons.Info,
                            contentDescription = Strings.CD_INFO,
                            tint = White,
                            modifier = Modifier.size(Dimen.SPACING_30)
                        )
                    }
                }
            }
        }
    }
}
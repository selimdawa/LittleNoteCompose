package com.flatcode.littlenote.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.flatcode.littlenote.ui.theme.AppIcons
import com.flatcode.littlenote.ui.theme.Dimen
import com.flatcode.littlenote.ui.theme.Strings
import com.flatcode.littlenote.ui.theme.White
import com.flatcode.littlenote.utils.DATA.MC_BG

@Composable
fun ToolbarAddEdit(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    actionIcon: ImageVector? = null,
    onActionClick: (() -> Unit)? = null
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimen.TOOLBAR_HEIGHT)
            .padding(Dimen.SPACING_10),
        shape = RoundedCornerShape(Dimen.CARD_CORNER_RADIUS_SMALL),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
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
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.size(Dimen.SPACING_30)
                ) {
                    Icon(
                        imageVector = AppIcons.ArrowBack,
                        contentDescription = Strings.CD_BACK,
                        tint = White,
                        modifier = Modifier.size(Dimen.SPACING_30)
                    )
                }

                Text(
                    text = title,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 4.dp),
                    color = White,
                    fontSize = Dimen.TEXT_SIZE_21,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                if (actionIcon != null && onActionClick != null) {
                    IconButton(
                        onClick = onActionClick,
                        modifier = Modifier.size(Dimen.SPACING_30)
                    ) {
                        Icon(
                            imageVector = actionIcon,
                            contentDescription = null,
                            tint = White,
                            modifier = Modifier.size(Dimen.SPACING_30)
                        )
                    }
                } else {
                    Box(modifier = Modifier.size(Dimen.SPACING_30))
                }
            }
        }
    }
}
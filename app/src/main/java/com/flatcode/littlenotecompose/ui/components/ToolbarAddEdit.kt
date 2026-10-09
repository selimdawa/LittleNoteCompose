package com.flatcode.littlenotecompose.ui.components

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.flatcode.littlenotecompose.ui.theme.AppIcons
import com.flatcode.littlenotecompose.ui.theme.Dimen
import com.flatcode.littlenotecompose.ui.theme.Strings
import com.flatcode.littlenotecompose.ui.theme.White
import com.flatcode.littlenotecompose.utils.DATA.MC_BG
import com.flatcode.littlenotecompose.utils.noRippleClickable

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
            .height(Dimen.TOOLBAR_HEIGHT_2)
            .padding(horizontal = Dimen.SPACING_10, vertical = Dimen.SPACING_10),
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
                Icon(
                    imageVector = AppIcons.ArrowBack,
                    contentDescription = Strings.CD_BACK,
                    tint = White,
                    modifier = Modifier
                        .size(Dimen.SPACING_30)
                        .noRippleClickable { onBackClick() }
                )

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
                    Icon(
                        imageVector = actionIcon,
                        contentDescription = null,
                        tint = White,
                        modifier = Modifier
                            .size(Dimen.SPACING_30)
                            .noRippleClickable { onActionClick() }
                    )
                } else {
                    Box(modifier = Modifier.size(Dimen.SPACING_30))
                }
            }
        }
    }
}
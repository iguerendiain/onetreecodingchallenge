package onetree.ignacio.guerendiain.core.theme.defs

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import onetree.ignacio.guerendiain.core.theme.spec.TypographySpec

val DefaultTypographyDefs = TypographySpec(
    buttonText = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp
    ),
    defaultMediumText = TextStyle(
        fontWeight = FontWeight.Normal,
        lineHeight = 26.sp,
        fontSize = 16.sp
    ),
    defaultBigText = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
    ),
    defaultScreenTitle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    defaultInputValue = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    dialogTitle = TextStyle(
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    dialogDescription = TextStyle(
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    toolbarButton = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    itemTitle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp
    ),
    itemSubtitle = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
    )
)

package onetree.ignacio.guerendiain.core.theme.defs

import androidx.compose.ui.graphics.Color
import onetree.ignacio.guerendiain.core.theme.db.ColorDB
import onetree.ignacio.guerendiain.core.theme.spec.ColorPalette

val DarkColorPalette = ColorPalette(
    // Dialog
    dialogBg = ColorDB.GrayDark,
    dialogButtonOkBg = ColorDB.CyanDark,
    dialogButtonOkFg = ColorDB.White,
    dialogButtonCancelBg = ColorDB.RedMainDark,
    dialogButtonCancelFg = ColorDB.White,
    dialogButtonRetryBg = ColorDB.GrayMain,
    dialogButtonRetryFg = ColorDB.GrayLight1,
    dialogImageBgWarning = ColorDB.YellowMain,
    dialogImageTintWarning = ColorDB.White,
    dialogImageBgFatal = ColorDB.RedMainDark,
    dialogImageTintFatal = ColorDB.White,

    // Button
    defaultButton = ColorDB.White,
    importantButtonBg = ColorDB.RedMainDark,
    importantButtonFg = ColorDB.White,
    disabledButtonBg = ColorDB.GrayLight1,
    disabledButtonFg = ColorDB.GrayMain,
    mainButtonColorBg = ColorDB.CyanDark,
    mainButtonColorFg = ColorDB.White,

    // Tags
    tagBg = ColorDB.GrayMain,
    tagFg = ColorDB.White,
    tagUnselectedBorder = ColorDB.GrayLight2,
    tagSelectedBorder = ColorDB.CyanMain,

    // Common
    defaultScreenBackground = Color.Black,
    mainTextFieldBg = ColorDB.GrayDark,
    toolbarTextButton = ColorDB.BlueMain,
    cardBorder = ColorDB.GrayDark,
    itemSubtitle = ColorDB.GrayLight1,
    importantText = ColorDB.RedMain,
    defaultText = ColorDB.White,
    lightText = ColorDB.GrayLight1,
    textLink = ColorDB.BlueMain,
)
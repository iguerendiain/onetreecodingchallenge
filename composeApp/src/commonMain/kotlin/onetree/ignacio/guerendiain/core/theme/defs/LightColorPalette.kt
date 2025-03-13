package onetree.ignacio.guerendiain.core.theme.defs

import androidx.compose.ui.graphics.Color
import onetree.ignacio.guerendiain.core.theme.db.ColorDB
import onetree.ignacio.guerendiain.core.theme.spec.ColorPalette

val LightColorPalette = ColorPalette(
    // Dialog
    dialogBg = ColorDB.White,
    dialogButtonOkBg = ColorDB.CyanMain,
    dialogButtonOkFg = ColorDB.White,
    dialogButtonCancelBg = ColorDB.RedLight1,
    dialogButtonCancelFg = ColorDB.White,
    dialogButtonRetryBg = ColorDB.White,
    dialogButtonRetryFg = ColorDB.GrayMain,
    dialogImageBgWarning = ColorDB.YellowMain,
    dialogImageTintWarning = ColorDB.White,
    dialogImageBgFatal = ColorDB.RedLight1,
    dialogImageTintFatal = ColorDB.White,

    // Button
    defaultButton = ColorDB.White,
    importantButtonBg = ColorDB.RedMain,
    importantButtonFg = ColorDB.White,
    disabledButtonBg = ColorDB.GrayLight1,
    disabledButtonFg = ColorDB.GrayMain,
    mainButtonColorBg = ColorDB.CyanMain,
    mainButtonColorFg = ColorDB.White,

    // Tags
    tagBg = ColorDB.White,
    tagFg = ColorDB.GrayMain,
    tagUnselectedBorder = ColorDB.GrayLight2,
    tagSelectedBorder = ColorDB.CyanMain,

    // Common
    defaultScreenBackground = ColorDB.White,
    mainTextFieldBg = ColorDB.GrayLight3,
    toolbarTextButton = ColorDB.BlueMain,
    cardBorder = Color.Transparent,
    itemSubtitle = ColorDB.GrayLight1,
    importantText = ColorDB.RedMain,
    defaultText = ColorDB.GrayMain,
    lightText = ColorDB.GrayLight1,
    textLink = ColorDB.BlueMain,
)
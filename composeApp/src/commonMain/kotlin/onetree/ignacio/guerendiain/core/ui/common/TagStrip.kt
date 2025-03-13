package onetree.ignacio.guerendiain.core.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp

@Composable
fun TagStrip(
    tags: Array<String>,
    selectedTag: Int?,
    onSelectedTag: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    val locale = Locale.current

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 12.dp),
        modifier = modifier
    ) {
        items(tags.size){
            val tag = tags[it]
            val isSelected = it == selectedTag
            val displayTag = tag.capitalize(locale)

            Tag(
                text = displayTag,
                selected = isSelected,
                cb = { onSelectedTag(it) },
                modifier = Modifier.width(100.dp)
            )

        }
    }
}
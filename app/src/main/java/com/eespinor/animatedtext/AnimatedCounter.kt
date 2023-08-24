@file:OptIn(ExperimentalAnimationApi::class)

package com.eespinor.animatedtext

import android.view.WindowInsets.Side
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun AnimatedCounter(
    count: Int,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyLarge
) {
    var oldCounter by remember {
        mutableStateOf(count)
    }

    SideEffect {
        oldCounter = count
    }
    Row(modifier = modifier) {
        val countString = count.toString()
        val oldCounterString = oldCounter.toString()
        for (i in countString.indices) {
            val oldChar = oldCounterString.getOrNull(i)
            val newChar = countString[i]
            var char = if (oldChar == newChar) {
                oldCounterString[i]
            } else {
                countString[i]
            }
            AnimatedContent(targetState = char,
                label = "",
                transitionSpec = {
                    slideInVertically { it } with slideOutVertically { -it }
                }) { char ->
                Text(
                    text = char.toString(),
                    style = style,
                    softWrap = false
                )
            }
        }
    }
}
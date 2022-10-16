package com.bhagyapatel.compose_ui_practice

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.bhagyapatel.compose_ui_practice.ui.theme.Shapes

private val TAG = "compose_ui"

@ExperimentalMaterialApi
@Composable
fun ExpandableCard(
    title: String,
    titleFontSize: TextUnit = MaterialTheme.typography.subtitle1.fontSize,
    titleFontWeight: FontWeight = FontWeight.Bold,
    description: String,
    descriptionFontSize: TextUnit = MaterialTheme.typography.subtitle1.fontSize,
    shape: CornerBasedShape = Shapes.medium
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotateState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = shape,
        onClick = {
            expandedState = !expandedState
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier.weight(6f),
                    text = title,
                    fontSize = titleFontSize,
                    fontWeight = titleFontWeight,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                        .weight(1f)
                        .rotate(rotateState),
                    onClick = {
                        expandedState = !expandedState
                    }) {

                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down A rrow"
                    )
                }
            }

            if (expandedState) {
                Text(
                    text = description,
                    fontSize = descriptionFontSize,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            var inputText by remember { mutableStateOf("") }

            TextField(
                value = inputText,
                onValueChange = { newText ->
                    inputText = newText
                },
                label = {
                    Text(text = "TextField")
                },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Email,
                            contentDescription = "Email Icon"
                        )
                    }
                },
                trailingIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = "Check Icon"
                        )
                    }
                },
//                maxLines = 2, //maximum 2 lines visible ..then scroll
                singleLine = true, //converts into horizontal scrollbar and remove enter key
                keyboardOptions = KeyboardOptions(
                    keyboardType =  KeyboardType.Number,
                    imeAction = ImeAction.Send //observe the enter button of the phone
                ),
                keyboardActions = KeyboardActions(
                    onSend = {
                        Log.d(TAG, "ExpandableCard: clicked")
                        inputText = ""
                    }
                )
            )

            OutlinedTextField(
                value = inputText,
                onValueChange = { newText ->
                    inputText = newText
                },
                label = {
                    Text(text = "OutlinedTextField")
                },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Email,
                            contentDescription = "Email Icon"
                        )
                    }
                },
                trailingIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = "Check Icon"
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType =  KeyboardType.Number,
                    imeAction = ImeAction.Send //observe the enter button of the phone
                ),
                keyboardActions = KeyboardActions(
                    onSend = {
                        Log.d(TAG, "ExpandableCard: clicked")
                        inputText = ""
                    }
                )
            )
        }
    }
}


@ExperimentalMaterialApi
@Composable
@Preview
fun ExpandableCardPreview() {
    ExpandableCard(
        title = "Bhagya",
        description = "Lorem ipsum dolor sit amet. Eum quos aliquid et perspiciatis " +
                "alias qui totam amet est alias natus et distinctio accusamus? Hic quasi " +
                "numquam eos quia quaerat qui aspernatur dignissimos 33 voluptatem iure " +
                "ex delectus quaerat vel galisum labore est nostrum harum. Vel debitis " +
                "temporibus et doloremque iusto eos ratione galisum sit voluptatem dolor " +
                "et molestias temporibus et consequatur veniam."
    )
}
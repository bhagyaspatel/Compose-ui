package com.bhagyapatel.compose_ui_practice

import android.graphics.BlurMaskFilter
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.* //add this import if unexpected error comes
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.bhagyapatel.compose_ui_practice.ui.theme.Grey
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
    var isClicked by remember { mutableStateOf(false) }

    val list = listOf<String>(
        "Bhukhe bacche NGO", "Nange bacche NGO",
        "budhe bacche NGO", "javani wale budhe NGO", "tharki budhe NGO"
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
//        onClick = {
//            expandedState = !expandedState
//        }
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
                    keyboardType = KeyboardType.Number,
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
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Search //observe the enter button of the phone
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        Log.d(TAG, "ExpandableCard: clicked")
                        inputText = ""
                    }
                )
            )

            Surface(
                onClick = {
                    isClicked = !isClicked
                    Log.d(TAG, "google signup : clicked")
                },
                shape = Shapes.medium,
                border = BorderStroke(width = 1.dp, color = Color.LightGray),
                color = MaterialTheme.colors.surface,
                modifier = Modifier.padding(vertical = 20.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(
                            start = 12.dp, end = 16.dp,
                            top = 12.dp, bottom = 12.dp
                        )
                        .animateContentSize(
                            animationSpec = tween(
                                durationMillis = 300,
                                easing = LinearOutSlowInEasing
                            )
                        )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_google_logo),
                        contentDescription = "Google logo",
                        tint = Color.Unspecified //else google logo will be b&w
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = if (isClicked) "Creating account"
                        else "Sign Up with Google"
                    )

                    if (isClicked) {
                        Log.d(TAG, "ExpandableCard: inside clicked")
                        Spacer(modifier = Modifier.width(16.dp))
                        CircularProgressIndicator(
                            modifier = Modifier
                                .width(16.dp)
                                .height(16.dp),
                            strokeWidth = 2.dp,
                            color = MaterialTheme.colors.primary
                        )
                    }
                }
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                color = MaterialTheme.colors.background,
                shape = Shapes.medium
            ) {
                Column() {

                    Row() {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "hot notification icon"
                        )

                    }

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Grey)
                    ) {
                        items(list) { name ->
                            CardView(name)
                        }
                    }
                }
            }

            CoilImage()
        }
    }
}

@Composable
fun CoilImage() {
    Box(
        modifier = Modifier
            .height(150.dp)
            .width(150.dp),
        contentAlignment = Alignment.Center,
    ){
        val painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .placeholder(R.drawable.ic_placeholder)
                .data("https://img.freepik.com/free-vector/cute-ninja-with-sword-cartoon-flat-cartoon-style_138676-2762.jpg?w=2000")
                .transformations(
                    RoundedCornersTransformation()
                )
                .error(R.drawable.ic_baseline_error_outline_24)
                .crossfade(1000)
                .build()
        )
        val painterState = painter.state
        Image(painter = painter, contentDescription = "my ninja image")

//        if(painterState is AsyncImagePainter.State.Loading){
//            CircularProgressIndicator()
//        }
    }
}

@Composable
fun CardView(text: String) {
    Card(
        modifier = Modifier
            .fillMaxHeight()
            .width(100.dp)
            .padding(horizontal = 4.dp, vertical = 8.dp),
        shape = Shapes.medium
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(4.dp)
        )
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
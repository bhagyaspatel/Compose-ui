package com.bhagyapatel.compose_ui_practice

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.bhagyapatel.compose_ui_practice.ui.theme.Grey
import com.bhagyapatel.compose_ui_practice.ui.theme.Purple200
import com.bhagyapatel.compose_ui_practice.ui.theme.Shapes
import com.bhagyapatel.compose_ui_practice.ui.theme.Teal200
import kotlinx.coroutines.launch

private val TAG = "compose_ui"

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterialApi
@Composable
fun ExpandableCard(
    title: String,
    titleFontSize: TextUnit = MaterialTheme.typography.subtitle1.fontSize,
    titleFontWeight: FontWeight = FontWeight.Bold,
    description: String,
    descriptionFontSize: TextUnit = MaterialTheme.typography.subtitle1.fontSize,
    shape: CornerBasedShape = Shapes.medium,
    onClick: () -> Unit,
    message: String
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

    var scrollState = rememberScrollState()

    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )

    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            ){
                Text(
                    text = "Bottom Sheet",
                    fontSize = 16.sp
                )
            }
        },
        sheetBackgroundColor = colorResource(id = R.color.bg),
//        sheetPeekHeight = 100.dp
    ) {
        //TODO : The code which represents the above original screen

        Card(
            modifier = Modifier
                .fillMaxSize()
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            shape = shape
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .fillMaxWidth()
                    .padding(12.dp),
            ) {
                Button(
                    onClick = {
                        Log.d("home_screen", "ExpandedScreen : clicked")
                        onClick()
                    },
                    modifier = Modifier
                        .background(MaterialTheme.colors.onBackground)
                        .height(50.dp)
                        .width(130.dp)
                ) {
                    Text(
                        text = "Go Back!!",
                        color = MaterialTheme.colors.surface,
                        fontSize = MaterialTheme.typography.h6.fontSize,
                    )
                }

                Text(
                    text = "message from home screen: $message",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    color = MaterialTheme.colors.primary,
                )

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
                val maxLength = 6

                TextField(
                    value = inputText,
                    onValueChange = { newText ->
                        if (newText.length < maxLength)
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

                var outlineInputText by remember { mutableStateOf("") }

                OutlinedTextField(
                    value = outlineInputText,
                    onValueChange = { newText ->
                        outlineInputText = newText
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
                        .height(320.dp),
                    color = MaterialTheme.colors.background,
                    shape = Shapes.medium
                ) {
                    Column() {

                        Row(horizontalArrangement = Arrangement.SpaceBetween) {

                            Text(
                                text = "Urgent need"
                            )
                            Icon(
                                imageVector = Icons.Filled.Notifications,
                                contentDescription = "hot notification icon"
                            )
                        }

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Grey)
                        ) {
                            list.forEach { ngoName ->
                                stickyHeader {
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(Color.LightGray)
                                            .padding(12.dp),
                                        text = "Ngo"
                                    )
                                }
                                items(2) { num ->
                                    Text(
                                        text = "${num}"
                                    )
                                    CardView(ngoName)
                                }
                            }
                        }
                    }
                }
                CoilImage()

                var password by rememberSaveable { mutableStateOf("") }
                var isVisible by rememberSaveable { mutableStateOf(true) }
                val icon = if (isVisible)
                    painterResource(id = R.drawable.ic_baseline_visibility_24)
                else
                    painterResource(id = R.drawable.ic_baseline_visibility_off_24)

                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    trailingIcon = {
                        IconButton(onClick = { isVisible = !isVisible }) {
                            Icon(
                                painter = icon,
                                contentDescription = "Visibility icon"
                            )
                        }
                    },
                    visualTransformation = if (isVisible) VisualTransformation.None
                    else PasswordVisualTransformation()
                )

                val gradient: Brush = Brush.horizontalGradient(
                    colors = listOf(Purple200, Teal200, Color.Yellow, Color.Red)
                )

                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent
                    ),
                    contentPadding = PaddingValues(), //try removing this and see the result
                    onClick = {}
                ) {
                    Box(
                        modifier = Modifier
                            .background(gradient)
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Click", color = Color.Black)
                    }
                }

                var value by rememberSaveable { mutableStateOf(0) }
                CircularIndicator(indicatorValue = value)

                TextField(
                    value = value.toString(),
                    onValueChange = {
                        value = if (!it.isNullOrEmpty()) {
                            it.toInt()
                        } else {
                            0
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .width(200.dp)
                        .align(alignment = Alignment.CenterHorizontally),

                    )
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent
                    ),
                    contentPadding = PaddingValues(), //try removing this and see the result
                    onClick = {
                        scope.launch {
                            if(sheetState.isCollapsed)
                                sheetState.expand()
                            else
                                sheetState.collapse()
                        }
                    }
                ) {
                    Box(
                        modifier = Modifier
                            .background(Color.Gray)
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(text = "Toggle Bottomsheet", color = Color.Black)
                    }
                }

                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent
                    ),
                    contentPadding = PaddingValues(), //try removing this and see the result
                    onClick = {}
                ) {
                    Box(
                        modifier = Modifier
                            .background(gradient)
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Click", color = Color.Black)
                    }
                }
            }
        }
    }

}

@Composable
fun CircularIndicator(
    canvasSize: Dp = 300.dp,
    indicatorValue: Int = 0,
    maxIndicatorValue: Int = 1000,
    backgroundIndicatorColor: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.1f),
    backgroundIndicatorStrokeWidth: Float = 100f,
    foregroundIndicatorColor: Color = MaterialTheme.colors.primary,
    foregroundIndicatorStrokeWidth: Float = 60f,
    bigTextFontSize: TextUnit = MaterialTheme.typography.h3.fontSize,
    bigTextColor: Color = MaterialTheme.colors.onSurface,
    bigTextSuffix: String = "GB",
    smallText: String = "Remaining",
    smallTextFontSize: TextUnit = MaterialTheme.typography.h6.fontSize,
    smallTextColor: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.3f)
) {

    var animatedIndicatorValue by remember { mutableStateOf(0f) }

    var allowedIndicatorValue by remember { mutableStateOf(maxIndicatorValue) }
    allowedIndicatorValue = if (indicatorValue <= maxIndicatorValue)
        indicatorValue
    else
        maxIndicatorValue

    //TODO : LaunchedEffect() runs whenever key value changes
    LaunchedEffect(key1 = allowedIndicatorValue) {
        animatedIndicatorValue = allowedIndicatorValue.toFloat()
    }

    val percentage = (animatedIndicatorValue / maxIndicatorValue) * 100

    val sweepAngle by animateFloatAsState(
        targetValue = (2.4 * percentage).toFloat(), //2.4 bcz:: our maximum value can be 240 only; targetValue will be our sweepAngle
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutSlowInEasing
        )
    )

    val recievedValue by animateIntAsState(
        targetValue = allowedIndicatorValue,
        animationSpec = tween(1000)
    )

    val animatedBigTextColor by animateColorAsState(
        targetValue =
        if (recievedValue == 0)
            MaterialTheme.colors.onSurface.copy(alpha = 0.3f)
        else
            bigTextColor,
        animationSpec = tween(1000)
    )

    Column(
        modifier = Modifier
            .size(canvasSize)
            .fillMaxWidth()
            .drawBehind {
                val componentSize = size / 1.25f
                //here "size" is the variable provided by DrawScope of type Size
                backgroundIndicator(
                    componentSize = componentSize,
                    indicatorColor = backgroundIndicatorColor,
                    indicatorStrokeWidth = backgroundIndicatorStrokeWidth
                )
                foregroundIndicator(
                    sweepAngle = sweepAngle,
                    componentSize = componentSize,
                    indicatorColor = foregroundIndicatorColor,
                    indicatorStrokeWidth = foregroundIndicatorStrokeWidth,
                )
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmbeddedElement(
            bigText = recievedValue,
            bigTextFontSize = bigTextFontSize,
            bigTextColor = animatedBigTextColor,
            bigTextSuffix = bigTextSuffix,
            smallText = smallText,
            smallTextFontSize = smallTextFontSize,
            smallTextColor = smallTextColor
        )
    }
}

fun DrawScope.backgroundIndicator(
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float,
    indicatorStrokeCap: StrokeCap = StrokeCap.Round
) {
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 150f,
        sweepAngle = 240f,
        useCenter = false, //try doing true: st pt and ending pt will be connected to the center of the arc
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = indicatorStrokeCap
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )
    )
}

fun DrawScope.foregroundIndicator(
    sweepAngle: Float,
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float,
    indicatorStrokeCap: StrokeCap = StrokeCap.Round
) {
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 150f,
        sweepAngle = sweepAngle,
        useCenter = false, //try doing true: st pt and ending pt will be connected to the center of the arc
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = indicatorStrokeCap
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )
    )
}

@Composable
fun EmbeddedElement(
    bigText: Int,
    bigTextFontSize: TextUnit,
    bigTextColor: Color,
    bigTextSuffix: String,
    smallText: String,
    smallTextFontSize: TextUnit,
    smallTextColor: Color
) {
    Text(
        text = smallText,
        color = smallTextColor,
        fontSize = smallTextFontSize,
        textAlign = TextAlign.Center
    )

    Text(
        text = "$bigText ${bigTextSuffix.take(2)}",
        color = bigTextColor,
        fontSize = bigTextFontSize,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun CoilImage() {
    Box(
        modifier = Modifier
            .height(150.dp)
            .width(150.dp),
        contentAlignment = Alignment.Center,
    ) {
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
            .fillMaxWidth()
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
                "et molestias temporibus et consequatur veniam.",
        onClick = {},
        message = "Message"
    )
}
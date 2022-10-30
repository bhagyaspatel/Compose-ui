package com.bhagyapatel.compose_ui_practice

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(mainViewModal: MainViewModal) {

    val searchWidgetState by mainViewModal.searchWidgetState
    val searchTextState by mainViewModal.searchTextState
    val context = LocalContext.current

    val navController = rememberNavController()
    Scaffold(
        topBar = {
            MainAppBar(
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChanged = {
                    mainViewModal.updateSearchTextState(it)
                },
                onCloseClicked = {
                    mainViewModal.updateSearchTextState("")
                    mainViewModal.updateSearchWidgetState(SearchWidgetState.CLOSED)
                },
                onSearchClicked = {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                },
                onSearchTriggered = {
                    mainViewModal.updateSearchWidgetState(SearchWidgetState.OPEN)
                }
            )
        },
        bottomBar = { BottomBar(navController = navController) }
    ) {
        BottomNavGraph(navController = navController)
    }
}

@Composable
fun MainAppBar(
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onTextChanged: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit
) { //this will decide weather to show DefaultAppBar() or SearchAppBar()
    when (searchWidgetState) {
        SearchWidgetState.CLOSED -> {
            DefaultTopBar (onSearchTriggered = onSearchTriggered)
        }

        SearchWidgetState.OPEN -> {
            SearchAppBar(
                text = searchTextState,
                onTextChanged = onTextChanged,
                onSearchClicked = onSearchClicked,
                onCloseClicked = onCloseClicked
            )
        }
    }
}

@Composable
fun DefaultTopBar(onSearchTriggered: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = "Home")
        },
        actions = {
            IconButton(
                onClick = { onSearchTriggered() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search icon",
                    tint = Color.White
                )
            }
        }
    )
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChanged: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp), //bcz height of the topbar is fixed 56.dp decided by material ui
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.primary
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = {
                onTextChanged(it)
            },
            placeholder = {
                Text(
                    text = "Search here...",
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    color = Color.White
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.h6.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    onClick = { }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search icon",
                        tint = Color.White
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (text.isNotEmpty())
                            onTextChanged("")
                        else
                            onCloseClicked()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close icon",
                        tint = Color.White
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { onSearchClicked(text) }
            ),
            colors = TextFieldDefaults.textFieldColors( //if we dont specify this, there is a slight color differance in DefaultTopBar and SearchAppBar()
                backgroundColor = Color.Transparent,
                cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
            )
        )
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Profile,
        BottomBarScreen.Setting,
    )

    //we are going to observe currentBackStackEntryAsState() and whenever its value gets changed we
    //are going to get notified
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    //predefined composable function
    BottomNavigation() {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "navigation icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id) //we will reach to start destination on BackPressed
                launchSingleTop = true

                //if we dont write above two lines onBackPress we will revisit all previously visited screens again
            }
        }
    )
}


@Composable
@Preview
fun DefaultAppBarPreview() {
    DefaultTopBar(onSearchTriggered = {})
}

@Composable
@Preview
fun SearchAppBarPreview() {
    SearchAppBar(
        text = "Some random text",
        onTextChanged = {},
        onCloseClicked = {},
        onSearchClicked = {}
    )
}
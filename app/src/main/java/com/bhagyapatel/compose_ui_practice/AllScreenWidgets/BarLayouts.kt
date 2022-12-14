package com.bhagyapatel.compose_ui_practice

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bhagyapatel.compose_ui_practice.Routes.BottomBarScreen
import com.bhagyapatel.compose_ui_practice.AllScreenWidgets.SearchWidgetState
import com.bhagyapatel.compose_ui_practice.DataClasses.MenuItems
import com.bhagyapatel.compose_ui_practice.HomeAndUIScreens.DrawerBody
import com.bhagyapatel.compose_ui_practice.HomeAndUIScreens.DrawerHeader
import com.bhagyapatel.compose_ui_practice.ViewModals.MainViewModal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun BarLayouts(mainViewModal: MainViewModal) {

    val searchWidgetState by mainViewModal.searchWidgetState
    val searchTextState by mainViewModal.searchTextState
    val context = LocalContext.current

    val navController = rememberNavController()

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
//        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        //dragging option to toggle drawer is only available when drawer is open

        drawerContent = {
            DrawerHeader()
            DrawerBody(
                menuItems = listOf(
                    MenuItems(
                        id = "about us",
                        title = "About us",
                        contentDescription = "About us page",
                        icon = Icons.Default.Info
                    ),
                    MenuItems(
                        id = "contact us",
                        title = "Contact us",
                        contentDescription = "Contact us page",
                        icon = Icons.Default.Phone
                    ),
                    MenuItems(
                        id = "Sign Out",
                        title = "Sign Out",
                        contentDescription = "Sign out button",
                        icon = Icons.Default.Close
                    )
                ),
                onItemClicked = {
                    Toast.makeText(context, "${it.title} clicked", Toast.LENGTH_SHORT).show()
                }
            )
        },
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
                },
                onNavigationDrawerTriggered = {
//the animation for opening the drawer takes some time so we need to perform this task
//inside a coroutine
                    scope.launch {
                        Log.d("TAG", "MainAppBar: is drawer open: ${scaffoldState.drawerState.isOpen}")
                        scaffoldState.drawerState.open()
                        Log.d("TAG", "MainAppBar: is drawer open: ${scaffoldState.drawerState.isOpen}")
                    }
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
    onSearchTriggered: () -> Unit,
    onNavigationDrawerTriggered: () -> Unit
) { //this will decide weather to show DefaultAppBar() or SearchAppBar()
    when (searchWidgetState) {
        SearchWidgetState.CLOSED -> {
            DefaultTopBar(
                onSearchTriggered = onSearchTriggered,
                onNavigationDrawerTriggered = onNavigationDrawerTriggered
            )
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
fun DefaultTopBar(
    onSearchTriggered: () -> Unit,
    onNavigationDrawerTriggered: () -> Unit
) {
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
        },
        navigationIcon = {
            IconButton(
                onClick = onNavigationDrawerTriggered,
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu Icon "
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
            if (screen.badgeCount > 0) {
                BadgedBox(
                    badge = {
                        Badge { Text(text = screen.badgeCount.toString()) }
                    }
                ) {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = "navigation icon"
                    )
                }
            }

            Icon(
                imageVector = screen.icon,
                contentDescription = "navigation icon"
            )
        },
        selected = currentDestination?.route == screen.route,

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
    DefaultTopBar(onSearchTriggered = {}, onNavigationDrawerTriggered = {})
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
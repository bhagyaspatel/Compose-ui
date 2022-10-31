package com.bhagyapatel.compose_ui_practice.HomeAndUIScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bhagyapatel.compose_ui_practice.DataClasses.MenuItems
import com.bhagyapatel.compose_ui_practice.R

@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier.fillMaxWidth()
            .height(200.dp),
        ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.mountain),
            contentDescription = "drawer header image"
        )
    }
}


@Composable
fun DrawerBody(
    menuItems: List<MenuItems>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClicked: (MenuItems) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(menuItems) { item ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onItemClicked(item)
                }
                .padding(16.dp)
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.contentDescription
                )

                Spacer(modifier = modifier.width(4.dp))

                Text(
                    text = item.title,
                    style = itemTextStyle,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
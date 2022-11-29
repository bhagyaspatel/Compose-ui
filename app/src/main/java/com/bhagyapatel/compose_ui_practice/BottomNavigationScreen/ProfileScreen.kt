package com.bhagyapatel.compose_ui_practice.BottomNavigationScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bhagyapatel.compose_ui_practice.ViewModals.ProfileViewModal
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ProfileScreen() {
    val viewModal = viewModel<ProfileViewModal>()
    val isLoading by viewModal.isLoading.collectAsState()

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

    SwipeRefresh(
        state =  swipeRefreshState,
//        onRefresh = viewModal::loadData
        onRefresh = { viewModal.loadData() },
        indicator = {state, refreshTrigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = refreshTrigger,
                backgroundColor = Color.Cyan,
                contentColor = Color.Blue
            )
        }
    ){
        //need to have a scrollable container here

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ){
            items(50){
                Text(
                    text = "Item",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(weight = 500)

                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen ()
}
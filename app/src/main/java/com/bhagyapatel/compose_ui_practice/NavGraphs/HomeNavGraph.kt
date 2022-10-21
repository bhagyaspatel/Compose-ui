package com.bhagyapatel.compose_ui_practice.NavGraphs

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.*
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.bhagyapatel.compose_ui_practice.*

@OptIn(ExperimentalMaterialApi::class)
fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {

    navigation(
        startDestination = Routes.Home.route,
        route = HOME_ROUTE
    ) {
        composable(
            route = Routes.Home.route,
            arguments = listOf(navArgument("msg") {
                type = NavType.IntType
                defaultValue = 0
            })
        ) {
            val id = it.arguments?.getString("id")?.toInt()

            HomeScreen(id,
                onGoClick = {
                    navController.navigate(route = Routes.Ui.passMessage(it))
                }, onAuthClick = {
                    navController.navigate(route = AUTHENTICATION_ROUTE)
                }
            )
        }

        composable(
            route = Routes.Ui.route,
            arguments = listOf(navArgument("message") {
                type = NavType.StringType
            })
        ) {

            val message = it.arguments?.getString(MESSAGE_ARGS_KEY).toString()

            ExpandableCard(
                title = "Bhagya",
                description = "Lorem ipsum dolor sit amet. Eum quos aliquid et perspiciatis " +
                        "alias qui totam amet est alias natus et distinctio accusamus? Hic quasi " +
                        "numquam eos quia quaerat qui aspernatur dignissimos 33 voluptatem iure " +
                        "ex delectus quaerat vel galisum labore est nostrum harum. Vel debitis " +
                        "temporibus et doloremque iusto eos ratione galisum sit voluptatem dolor " +
                        "et molestias temporibus et consequatur veniam.",
                onClick = {
//                    navController.navigate(route = Routes.Home.route) //problem :: addition to popBackStack
//                    navController.popBackStack()  //we could also press back button in this case
                    navController.navigate(route = Routes.Home.passMessage()) {
                        popUpTo(Routes.Home.route) {
                            inclusive = true
                        }
                    }
                },
                message = message
            )
        }
    }
}
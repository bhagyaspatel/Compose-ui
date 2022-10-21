package com.bhagyapatel.compose_ui_practice

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.bhagyapatel.compose_ui_practice.NavGraphs.authNavGraph
import com.bhagyapatel.compose_ui_practice.NavGraphs.homeNavGraph

@Composable
fun SetupHomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = HOME_ROUTE,
        route = ROOT_ROUTE
    ) { //route of the start destination
//        composable(
//            route = Routes.Home.route,
//            arguments = listOf(navArgument("msg"){
//                type = NavType.IntType
//                defaultValue = 0
//            })
//        ) {
//            val id = it.arguments?.getString("id")?.toInt()
//
//            HomeScreen(onClick = {
//                navController.navigate(route = Routes.Ui.passMessage(it))
//            }, id)
//        }
//
//        composable(
//            route = Routes.Ui.route,
//            arguments = listOf(navArgument("message"){
//                type = NavType.StringType
//            })
//        ) {
//
//            val message = it.arguments?.getString(MESSAGE_ARGS_KEY).toString()
//
//            ExpandableCard(
//                title = "Bhagya",
//                description = "Lorem ipsum dolor sit amet. Eum quos aliquid et perspiciatis " +
//                        "alias qui totam amet est alias natus et distinctio accusamus? Hic quasi " +
//                        "numquam eos quia quaerat qui aspernatur dignissimos 33 voluptatem iure " +
//                        "ex delectus quaerat vel galisum labore est nostrum harum. Vel debitis " +
//                        "temporibus et doloremque iusto eos ratione galisum sit voluptatem dolor " +
//                        "et molestias temporibus et consequatur veniam.",
//                onClick = {
////                    navController.navigate(route = Routes.Home.route) //problem :: addition to popBackStack
////                    navController.popBackStack()  //we could also press back button in this case
//                    navController.navigate(route = Routes.Home.passMessage()){
//                        popUpTo(Routes.Home.route){
//                            inclusive = true
//                        }
//                    }
//                },
//                message = message
//            )
//        }

//        navigation( //this is an extension function on navGraphBuilder :: we have separated this in diff file NavGraphHome
//            startDestination = Routes.Login.route,
//            route = AUTHENTICATION_ROUTE
//        ){
//            composable(route = Routes.Login.route){
//                LoginScreen {
//                    navController.navigate(route = Routes.Home.passMessage(1))
//                }
//            }
//            composable(route = Routes.Signup.route){
//                Signup {
//                    navController.navigate(route = Routes.Signup.route)
//                }
//            }
//        }

        homeNavGraph(navController)
        authNavGraph(navController)
    }
}

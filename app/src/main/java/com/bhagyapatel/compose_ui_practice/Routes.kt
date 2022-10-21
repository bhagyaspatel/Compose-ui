package com.bhagyapatel.compose_ui_practice

const val MESSAGE_ARGS_KEY = "message"

sealed class Routes(val route: String) {
    object Home : Routes(route = "home_screen?id={id}") { //-> ? specifies optional argument
        fun passMessage(id : Int = 0): String { //passed 0 as optional argument
            return "home_screen?id=$id"
        }
    }

    object Ui : Routes(route = "ui_screen/{$MESSAGE_ARGS_KEY}") {
        fun passMessage(message: String): String {
//            return "ui_screen/${message}"
            return this.route.replace(oldValue = "{$MESSAGE_ARGS_KEY}", newValue = message)
        }
    }
}

// home_screen?id={id}&name={name} :: for multiple optional arguments; just like urls

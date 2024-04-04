package org.d3if3121.wordz.navigation

sealed class Screen(val route: String) {
    data object Menu: Screen("MenuScreen")
    data object AddWords: Screen("AddWordsScreen")
    data object Quiz: Screen("QuizScreen")


}
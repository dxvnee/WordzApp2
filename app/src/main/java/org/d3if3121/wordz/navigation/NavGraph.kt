package org.d3if3121.wordz.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.d3if3121.wordz.model.WordsViewModel
import org.d3if3121.wordz.ui.screen.AddWordsScreen
import org.d3if3121.wordz.ui.screen.MenuScreen
import org.d3if3121.wordz.ui.screen.QuizScreen

@Composable
fun SetupNavGraph(viewModell: WordsViewModel, navController: NavHostController = rememberNavController()){
    val state by viewModell.state.collectAsState()
    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = Screen.Menu.route
    ){
        composable(route = Screen.Menu.route){
            MenuScreen(
                state = state,
                navController = navController,
                onEvent = viewModell::onEvent,

            )
        }
        composable(route = Screen.AddWords.route){
            AddWordsScreen(
                state = state,
                navController = navController,
                onEvent = viewModell::onEvent
            )
        }
        composable(route = Screen.Quiz.route){
            QuizScreen(
                state = state,
                navController = navController,
                onEvent = viewModell::onEvent,

                )
        }

    }

}

package com.example.mymovies
import android.annotation.SuppressLint
import android.os.Bundle

import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

/*
class MainActivity : AppCompatActivity() {
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MyApp {
                MovieScreen(viewModel = viewModel, navController = navController)
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(content: @Composable () -> Unit) {

    // A surface container using the 'background' color from the theme
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Movies") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            content()
        }
    }

}
*/
class MainActivity : AppCompatActivity() {
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MyApp(navController = navController) {
                MovieScreen(viewModel = viewModel, navController = navController)
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(navController: NavHostController, content: @Composable () -> Unit) {
    // A surface container using the 'background' color from the theme
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Movies") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            NavHost(navController, startDestination = "MovieScreen") {
                composable("MovieScreen") { content() }
                composable("MovieDetailScreen") { MovieDetailScreen(navController = navController, overview = "") }
                // Add more destinations as needed
            }
        }
    }
}


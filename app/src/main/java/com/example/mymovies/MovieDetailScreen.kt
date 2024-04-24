package com.example.mymovies

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

/*@Composable
fun MovieDetailScreen(overview: String) {
    // Retrieve selected movie details
    val selectedMovie = RetrofitInstance.selectedMovie
    val overview = selectedMovie?.overview


    // Display movie details if selectedMovie is not null
    selectedMovie?.let { movie ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "Movie Detail Screen")
            Text(text = "Title: ${overview}")
            Text(text = "Description: ${overview}")
            // Display other movie details here
        }
    }
}*/


@Composable
fun MovieDetailScreen(navController: NavController) {
    // Display "Hello, Movie Detail Screen" text
    Text(text = "Hello, Movie Detail Screen")
}

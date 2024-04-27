package com.example.mymovies

import Results
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.navigation.NavController
 fun setSelectedMovie(movie: Results) {
    RetrofitInstance.selectedMovie = movie
}
@Composable
fun MovieScreen(viewModel: MovieViewModel, navController: NavController) {
    val movies by viewModel.movies.observeAsState(emptyList<Results>())


    LaunchedEffect(Unit) {
        viewModel.fetchPopularMovies(
            apiKey = "c14c15c28109f082c13fc95d04b65361",
            )
    }

    // Display a loading indicator while data is being fetched
    if (movies.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        // Once data is available, display the movie titles
        MovieList(movieList = movies, navController = navController)
        println(movies.toString())
    }
}

@Composable
fun MovieList(movieList: List<Results>, navController: NavController) {
    LazyColumn {
        itemsIndexed(items = movieList) { index, movie ->
            // Wrap each item in a Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)

                    .clickable {
                        setSelectedMovie(movie)
                        // Navigate to MovieDetailScreen on click
                        navController.navigate("MovieDetailScreen")
                    },
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),

            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,

                ) {


                    Text(text = "${movie.title ?: "Unknown Title"}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp)

                    Spacer(Modifier.height(10.dp))

                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(280.dp, 438.dp)
                            .clip(RoundedCornerShape(16.dp)),

                        model = ImageRequest.Builder(LocalContext.current)
                            .data("${RetrofitInstance.imageBaseUrl}${movie.poster_path}")
                            .build(),
                        contentDescription = "Movie Poster",


                    )


                }
            }
        }
    }
}


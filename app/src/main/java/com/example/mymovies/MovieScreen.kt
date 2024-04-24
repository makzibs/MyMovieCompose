package com.example.mymovies

import Results
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest

@Composable
fun CreditCardScreen(viewModel: MovieViewModel) {
    val movies by viewModel.movies.observeAsState(emptyList<Results>())
    //val movieDetail by viewModel.moviede

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
        MovieList(movieList = movies)
        println(movies.toString())
    }
}

@Composable
fun MovieList(movieList: List<Results>) {
    LazyColumn {
        itemsIndexed(items = movieList) { index, movie ->
            // Wrap each item in a Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "${movie.title ?: "Unknown Title"}")
                    Log.d("Tag", "${movie.poster_path}")
                    Log.d("Tag", "${movie.toString()}")

                  /*  val painter = // You can customize image loading here if needed
                        rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current)
                                .data(data = "${RetrofitInstance.BASE_URL}${movie.posterPath}")
                                .apply<ImageRequest.Builder>(block = fun ImageRequest.Builder.() {
                                    // You can customize image loading here if needed
                                }).build()
                        )
                    Image(
                        painter = painter,
                        contentDescription = "Movie Poster",
                        modifier = Modifier
                            .size(280.dp, 438.dp) // Adjust size as needed
                    )*/
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("${RetrofitInstance.imageBaseUrl}${movie.poster_path}")
                            .build(),
                        contentDescription = "Movie Poster",
                        modifier = Modifier.size(280.dp, 438.dp)
                    )

                }
            }
        }
    }
}

/*
@Composable
fun MovieItem(movie: PopularMovie) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),

    ) {
        Column(
            modifier = Modifier
                .clickable { */
/* Handle click on movie item if needed *//*
 }
                .padding(16.dp)
        ) {
            Text(
                text = movie.title ?: "Unknown Title",
                style = MaterialTheme.typography.h6,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = movie.overview ?: "No overview available",
                style = MaterialTheme.typography.body2,
                color = Color.Gray
            )
        }
    }
}*/
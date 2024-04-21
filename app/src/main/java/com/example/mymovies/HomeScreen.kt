package com.example.mymovies

import MovieViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.LineHeightStyle
import coil.compose.rememberImagePainter
import java.lang.reflect.Modifier

@Composable
fun HomeScreen(movieViewModel: MovieViewModel) {
    val popularMovies = movieViewModel.popularMovies.observeAsState(emptyList<Results>())

    LazyColumn {
        items(popularMovies.value?.results ?: emptyList()) { movie ->
            MovieItem(movie)
        }
    }
}


@Composable
fun MovieItem(movie: Results) {
    // Display movie poster
    val painter = rememberImagePainter(data = movie.posterPath)
    Image(
        painter = painter,
        contentDescription = "Movie Poster",
        modifier = Modifier
            .height(250.dp)
            .align(LineHeightStyle.Alignment.CenterHorizontally),
    )

    Spacer(modifier = Modifier.height(8.dp))

    // Display movie title
    Text(
        text = movie.title,
        style = MaterialTheme.typography.h6
    )

    Spacer(modifier = Modifier.height(8.dp))

    // Display movie genres
    Text(
        text = movie.genres.joinToString(),
        style = MaterialTheme.typography.body2
    )
}
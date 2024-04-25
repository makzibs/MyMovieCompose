package com.example.mymovies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun MovieDetailScreen(overview: String, navController: NavHostController) {
    // Retrieve selected movie details
    val selectedMovie = RetrofitInstance.selectedMovie
    //val overview = selectedMovie?.overview
    val customButtonColors = ButtonDefaults.buttonColors(
       Color(0xFF153448),
        contentColor = Color.White // Adjust text color as needed
    )


    // Display movie details if selectedMovie is not null
    selectedMovie?.let { movie ->
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)


        ) {

            Text(text = "${movie.title}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp)
            Spacer(modifier = Modifier.height(10.dp))
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(100.dp, 200.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data("${RetrofitInstance.imageBaseUrl}${movie.poster_path}")
                    .build(),
                contentDescription = "Movie Poster",
                )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "${movie.overview}")
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = " ${movie.release_date}".substringBefore("-"),
                        fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(100.dp))
                Row {
                    Text(text = "Imdb Rating",
                        fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "%.1f".format(movie.vote_average))
                }
               
            }
            Spacer(modifier = Modifier.height(200.dp))
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(top = 20.dp),
                colors = customButtonColors
            ) {
                Text(text = "Go Back")
            }
            // Display other movie details here
        }
    }
}


/*@Composable
fun MovieDetailScreen(navController: NavController) {
    // Display "Hello, Movie Detail Screen" text
    Text(text = "Hello, Movie Detail Screen")
}*/

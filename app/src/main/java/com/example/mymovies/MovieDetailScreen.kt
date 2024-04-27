package com.example.mymovies

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
    val customCardColors = CardDefaults.cardColors(
        containerColor =  Color(0xFF153448), // Change background color
        contentColor = Color.White // Change text color
    )



    // Display movie details if selectedMovie is not null
    selectedMovie?.let { movie ->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),

        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)


            ) {

                Text(
                    text = "${movie.title}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Box(modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(100.dp, 200.dp),

                        model = ImageRequest.Builder(LocalContext.current)
                            .data("${RetrofitInstance.imageBaseUrl}${movie.poster_path}")
                            .build(),
                        contentDescription = "Movie Poster",
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    //backgroundColor = Color(0xFF153448),
                    colors = customCardColors,

                ) {
                    Text(
                        text = "${movie.overview}",
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center,
                        maxLines = 5, // Set maximum number of lines
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = " ${movie.release_date}".substringBefore("-"),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(100.dp))
                    Row {
                        Text(
                            text = stringResource(R.string.rating),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "%.1f".format(movie.vote_average))
                    }

                }
                Spacer(modifier = Modifier.height(50.dp))
                CallButton(phoneNumber = "0413170401")

                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.padding(top = 20.dp),
                    colors = customButtonColors
                ) {
                    Text(
                        text = stringResource(R.string.goBack),
                        fontWeight = FontWeight.Bold
                    )
                }
                // Display other movie details here
            }
        }
    }
}



@Composable
fun CallButton(phoneNumber: String) {
    val context = LocalContext.current
    val makePhoneCall: () -> Unit = {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
        context.startActivity(intent)
    }

    Button(
        onClick = makePhoneCall,
        colors = ButtonDefaults.buttonColors(
            Color(0xFF153448),
            contentColor = Color.White
        )
    ) {
        Text(
            text = stringResource(R.string.call),
            fontWeight = FontWeight.Bold
        )
    }
}



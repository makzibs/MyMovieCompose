package com.example.mymovies


import Results
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val API_KEY = "c14c15c28109f082c13fc95d04b65361"
    private const val AUTH_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjMTRjMTVjMjgxMDlmMDgyYzEzZmM5NWQwNGI2NTM2MSIsInN1YiI6IjY1ZDJlZjE2MjhkN2ZlMDE0OTM0NDRmOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.1i2acStIfdOf0nkvxnSbb274Zw8W7ytFMky2QMlCUCg"
    const val imageBaseUrl = "https://image.tmdb.org/t/p/w500"
    var selectedMovie: Results? = null

    // Interceptor to add Authorization header to every request
    private val authInterceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val newRequest = originalRequest.newBuilder()
                .header("Authorization", "Bearer $AUTH_TOKEN")
                .build()
            val response = chain.proceed(newRequest)
            return response;
        }
    }

    // OkHttpClient with the authorization interceptor
    private val client = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    // Retrofit instance
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Service interface
    val creditCardService: MovieService by lazy {
        retrofit.create(MovieService::class.java)
    }
}

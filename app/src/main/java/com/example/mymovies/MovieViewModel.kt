package com.example.mymovies


import Results

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch



class MovieViewModel : ViewModel() {
    private val _movies = MutableLiveData<List<Results>>()
    val movies: LiveData<List<Results>> = _movies

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val movieService = RetrofitInstance.creditCardService

    fun fetchPopularMovies(apiKey: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = movieService.getPopularMovies("c14c15c28109f082c13fc95d04b65361")
                if (response.isSuccessful) {
                    _movies.value = response.body()?.results ?: emptyList()
                } else {
                    _error.value = "Failed to fetch popular movies: ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = "Error fetching popular movies: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
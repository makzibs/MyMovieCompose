import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mymovies.PopularMovie
import com.example.mymovies.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel : ViewModel() {
    private val _popularMovies = MutableLiveData<PopularMovie>()
    val popularMovies: LiveData<PopularMovie> get() = _popularMovies

    init {
        fetchPopularMovies()
    }

    private fun fetchPopularMovies() {
        viewModelScope.launch {
            try {
                val popularMovies = withContext(Dispatchers.IO) {
                    RetrofitClient.movieApiService.fetchPopularMovies("c14c15c28109f082c13fc95d04b65361")
                }
                _popularMovies.value = popularMovies
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
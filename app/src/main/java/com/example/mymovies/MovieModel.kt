data class PopularMovie(
    val dates: Dates?,
    val page: Int?,
    val results: List<Results>?,
    val totalPages: Int?,
    val totalResults: Int?
)

data class Dates(
    val maximum: String?,
    val minimum: String?
)

data class Results(
    val adult: Boolean?,
    val backdropPath: String?,
    val genreIds: List<Int>?,
    val id: Int?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val voteCount: Int?
)


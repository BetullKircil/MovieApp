import com.betulkircil.movieapp_clean_arthitecture.domain.model.MovieList

data class MovieListState(
    val isLoading : Boolean = false,
    val movieList : List<MovieList> = emptyList(),
    val error : String = "",
    val search : String = "pulp"
)
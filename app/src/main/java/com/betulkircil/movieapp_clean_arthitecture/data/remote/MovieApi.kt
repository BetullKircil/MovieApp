package com.betulkircil.movieapp_clean_arthitecture.data.remote

import com.betulkircil.movieapp_clean_arthitecture.data.remote.dto.MovieDetailDto
import com.betulkircil.movieapp_clean_arthitecture.data.remote.dto.MovieListDto
import com.betulkircil.movieapp_clean_arthitecture.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("") //endpoint yok
    suspend fun getMovieList(
        @Query("s") searhString : String,
        @Query("apikey") apikey : String = Constants.API_KEY
    ) : MovieListDto

    @GET("")
    suspend fun getMovieDetail(
        @Query("i") imdbId : String,
        @Query("apikey") apikey : String = Constants.API_KEY
    ) : MovieDetailDto
}
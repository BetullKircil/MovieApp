package com.betulkircil.movieapp_clean_arthitecture.domain.useCase.getMovie

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.betulkircil.movieapp_clean_arthitecture.data.remote.dto.toMovieList
import com.betulkircil.movieapp_clean_arthitecture.domain.model.MovieList
import com.betulkircil.movieapp_clean_arthitecture.domain.repository.MovieRepository
import com.betulkircil.movieapp_clean_arthitecture.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMovieUseCase@Inject constructor(private val repository : MovieRepository) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getMovies(search : String) : Flow<Resource<List<MovieList>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val movieList = repository.getMovies(search)
                if(movieList.Response.equals("True")){
                    emit(Resource.Success(movieList.toMovieList()))
                }
                else{
                    emit(Resource.Error(message = "No movie found"))
                }
            }
            catch (e : IOError){
                emit(Resource.Error(message = "No internet connection"))
            }
            catch (e : HttpException){
                emit(Resource.Error(message = e.localizedMessage ?: "Error"))
            }
        }
    }
}
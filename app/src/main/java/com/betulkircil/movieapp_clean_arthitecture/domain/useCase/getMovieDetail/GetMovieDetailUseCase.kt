package com.betulkircil.movieapp_clean_arthitecture.domain.useCase.getMovieDetail

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.betulkircil.movieapp_clean_arthitecture.data.remote.dto.toMovieDetail
import com.betulkircil.movieapp_clean_arthitecture.domain.model.MovieDetail
import com.betulkircil.movieapp_clean_arthitecture.domain.repository.MovieRepository
import com.betulkircil.movieapp_clean_arthitecture.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val repository: MovieRepository){
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getMovieDeatil(imdbId : String) : Flow<Resource<MovieDetail>> {
        return flow {
            try {
                emit(Resource.Loading())
                val movieDetail = repository.getMovieDetail(imdbId = imdbId)
                emit(Resource.Success(movieDetail.toMovieDetail()))
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
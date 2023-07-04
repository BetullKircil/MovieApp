package com.betulkircil.movieapp_clean_arthitecture.presentation.movieDetail

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betulkircil.movieapp_clean_arthitecture.domain.useCase.getMovieDetail.GetMovieDetailUseCase
import com.betulkircil.movieapp_clean_arthitecture.utils.Constants.IMDB_ID
import com.betulkircil.movieapp_clean_arthitecture.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val stateHandle : SavedStateHandle
) : ViewModel(){

    init {
        stateHandle.get<String>(IMDB_ID)?.let {
            getMovieDetails(it)
        }
    }
    private val _state = mutableStateOf<MovieDetailState>(MovieDetailState())
    val state : State<MovieDetailState> = _state
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun getMovieDetails(imdbId : String){
        getMovieDetailUseCase.getMovieDeatil(imdbId).onEach {
            when(it){
                is Resource.Loading -> {
                    //_state.value = MovieDetailState(isLoading = true)
                }
                is Resource .Success -> {
                    _state.value = MovieDetailState(movie = it.data)
                }
                is Resource.Error -> {
                    _state.value = MovieDetailState(error = it.message ?: "Error")
                }
            }
        }.launchIn(viewModelScope)

    }
}
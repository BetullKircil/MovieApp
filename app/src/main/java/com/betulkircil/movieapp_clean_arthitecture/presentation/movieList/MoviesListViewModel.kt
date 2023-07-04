package com.betulkircil.deneme.presentation.movie_list

import MovieListState
import android.os.Build
import android.os.ext.SdkExtensions
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betulkircil.movieapp_clean_arthitecture.domain.useCase.getMovie.GetMovieUseCase
import com.betulkircil.movieapp_clean_arthitecture.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.lang.Error
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(  //use case'i inject ediyoruz
    private val getMovieUseCase: GetMovieUseCase,
) : ViewModel() {
    private val _state  = mutableStateOf<MovieListState>(MovieListState()) //mutable state, burada kullanacagiz
    val state : State<MovieListState> = _state //immutable state, dosyanin disinda kullanacagiz

    private var job : Job? = null

    init{
        getMovieList(_state.value.search)
    }

    private  fun getMovieList(search: String){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && SdkExtensions.getExtensionVersion(
                Build.VERSION_CODES.S) >= 7) {
            job?.cancel()  //eger bir job varsa iptal et ve yeni gelen job'ı al
            job = getMovieUseCase.getMovies(search = search).onEach {
                when(it){  //resource'yi isliyoruz
                    is Resource.Success -> {
                        _state.value = MovieListState(movieList = it.data ?: emptyList())
                    }
                    is Resource.Error -> {
                        _state.value = MovieListState(error = it.message ?: "Error")
                    }

                    is Resource.Loading -> {
                        _state.value = MovieListState(isLoading = true)
                    }
                }


            }.launchIn(viewModelScope)
        }
    }
    fun onEvent(event : MovieListEvent){
        when(event){
            is MovieListEvent.Search -> {
                getMovieList(event.searchString)
            }
        }
    }

}


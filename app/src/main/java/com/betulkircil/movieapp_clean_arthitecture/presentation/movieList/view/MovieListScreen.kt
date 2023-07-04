package com.betulkircil.movieapp_clean_arthitecture.presentation.movieList.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.betulkircil.deneme.presentation.movie_list.MovieListEvent
import com.betulkircil.deneme.presentation.movie_list.MoviesListViewModel
import com.betulkircil.deneme.presentation.movie_list.components.MovieListRow
import com.betulkircil.deneme.presentation.movie_list.components.SearchBar
import com.betulkircil.movieapp_clean_arthitecture.presentation.Screen

@Composable
fun MovieListScreen(
    navController : NavController,
    viewModel: MoviesListViewModel = hiltViewModel()  //vievmodel'i hilt ile inject ettik
) {
    val state = viewModel.state.value  //sayfanın state'ini veriyor, isLoading mi, error mu ya da isSucces mi
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)){
        Column() {
            SearchBar(modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp),
                hint = "pulp",
                onSearch = {
                    viewModel.onEvent(MovieListEvent.Search(it))  //viewmodeldeki search stateine girilen it parameteresi search edilmek icin gonderilir
            })
                LazyColumn(modifier = Modifier.fillMaxSize()){
                    items(state.movieList){movie ->
                        MovieListRow(movie = movie, onItemClick = {
                            navController.navigate(Screen.MovieDetailScreen.route + "/${movie.imdbID}")
                        })
                    }
                }
        }

    }

}
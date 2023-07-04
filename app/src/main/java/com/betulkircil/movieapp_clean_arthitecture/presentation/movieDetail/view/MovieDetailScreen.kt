package com.betulkircil.movieapp_clean_arthitecture.presentation.movieDetail.view

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.betulkircil.movieapp_clean_arthitecture.presentation.movieDetail.MovieDetailViewModel
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun MovieDetailScreen(
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel()
) {
    val state = movieDetailViewModel.state.value

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black),
    contentAlignment= Alignment.Center){
        state.movie?.let {
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
                    ){
                Image(painter = rememberImagePainter(data = it.Poster), contentDescription = it.Title, modifier = Modifier
                    .padding(16.dp)
                    .size(300.dp, 300.dp)
                    .clip(
                        RectangleShape
                    )
                    .align(CenterHorizontally))
                

                Text(text = it.Title, textAlign = TextAlign.Center, color = Color.White, modifier = Modifier.padding(14.dp))
                Text(text = it.Year, textAlign = TextAlign.Center, color = Color.White, modifier = Modifier.padding(14.dp))
                Text(text = it.Country, textAlign = TextAlign.Center, color = Color.White, modifier = Modifier.padding(14.dp))
                Text(text = it.Director, textAlign = TextAlign.Center, color = Color.White, modifier = Modifier.padding(14.dp))
                Text(text = it.imdbRating, textAlign = TextAlign.Center, color = Color.White, modifier = Modifier.padding(14.dp))
                if (state.error.isNotBlank()){  //if there is an error
                    Text(text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp)
                        .align(CenterHorizontally))

                }
                if(state.isLoading){
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
    
}
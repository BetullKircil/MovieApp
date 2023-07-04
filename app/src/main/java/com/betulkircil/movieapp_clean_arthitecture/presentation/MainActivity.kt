package com.betulkircil.movieapp_clean_arthitecture.presentation

import android.os.Build
import android.os.Bundle
import android.os.ext.SdkExtensions
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.betulkircil.movieapp_clean_arthitecture.presentation.movieDetail.view.MovieDetailScreen
import com.betulkircil.movieapp_clean_arthitecture.presentation.movieList.view.MovieListScreen
import com.betulkircil.movieapp_clean_arthitecture.presentation.ui.theme.MovieApp_clean_arthitectureTheme
import com.betulkircil.movieapp_clean_arthitecture.utils.Constants.IMDB_ID

class MainActivity : ComponentActivity() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieApp_clean_arthitectureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationComposable()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun NavigationComposable() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MovieListScreen.route){
        composable(route = Screen.MovieListScreen.route){
            MovieListScreen(navController = navController)
        }
        composable(route = Screen.MovieDetailScreen.route + "/{${IMDB_ID}}"){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && SdkExtensions.getExtensionVersion(
                    Build.VERSION_CODES.S) >= 7) {
                MovieDetailScreen()
            }
        }
    }
}
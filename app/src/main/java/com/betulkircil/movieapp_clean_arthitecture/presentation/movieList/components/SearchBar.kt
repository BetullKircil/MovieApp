@file:OptIn(ExperimentalMaterial3Api::class)

package com.betulkircil.deneme.presentation.movie_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    modifier : Modifier,
    hint : String = "",
    onSearch : (String) -> Unit = {}  //hicbi sey donmeyen lambda fonksiyonu
) {
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }
    var searchText by remember {
        mutableStateOf("")
    }
    Box(modifier = Modifier.fillMaxWidth().padding(20.dp), contentAlignment = Alignment.Center) {
        TextField(value = searchText, onValueChange = {
            searchText = it
        }, keyboardActions = KeyboardActions(onDone = {
            onSearch(searchText)
        }), maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            shape = RoundedCornerShape(30.dp),
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp)
                .onFocusChanged {
                    isHintDisplayed = it.isFocused != true && searchText.isEmpty()
                }
        )
        if(isHintDisplayed){
            Text(
                text = hint,
                color = Color.LightGray,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp))
        }
    }
}
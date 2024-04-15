package com.anos.demo.ui.search

import android.annotation.SuppressLint
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchRoute(
    onBackClick: () -> Unit,
    onSearching: (String) -> Unit,
) {
    SearchScreen(onBackClick, onSearching)
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    onBackClick: () -> Unit,
    onSearching: (String) -> Unit
) {
    var searchInputValue by remember { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }
    val requestFocus = remember { FocusRequester() }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)
                    .height(56.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = { onBackClick() }
                ) {
                     Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
                Spacer(modifier = Modifier.width(8.dp))
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .height(44.dp)
                        .focusRequester(requestFocus),
                    value = searchInputValue,
                    singleLine = true,
                    onValueChange = {
                        searchInputValue = it
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onSearching(searchInputValue)
                        }
                    ),
                    decorationBox = {
                        TextFieldDefaults.DecorationBox(
                            value = searchInputValue,
                            innerTextField = it,
                            enabled = true,
                            singleLine = true,
                            visualTransformation = VisualTransformation.None,
                            interactionSource = interactionSource,
                            colors = TextFieldDefaults.colors(
                                cursorColor = Color.Black,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            prefix = {
                                Icon(
                                    imageVector = Icons.Outlined.Search,
                                    contentDescription = null
                                )
                            },
                            trailingIcon = {
                                IconButton(onClick = {
                                    searchInputValue = ""
                                }) {
                                    Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clear")
                                }
                            },
                            placeholder = { Text(
                                "Search by title or author",
                                maxLines = 1, softWrap = false, overflow = TextOverflow.Ellipsis
                            ) },
                            contentPadding = PaddingValues(
                                vertical = 4.dp,
                                horizontal = 4.dp
                            ),
                            container = {
                                OutlinedTextFieldDefaults.ContainerBox(
                                    enabled = true,
                                    isError = false,
                                    interactionSource = interactionSource,
                                    colors = OutlinedTextFieldDefaults.colors(),
                                    shape = RoundedCornerShape(8.dp),
                                    focusedBorderThickness = 0.7.dp,
                                    unfocusedBorderThickness = 0.7.dp
                                )
                            }
                        )
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    onSearching(searchInputValue)
                }) { Text(text = "Search",) }
                LaunchedEffect(Unit) {
                    requestFocus.requestFocus()
                }
            }
        },
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Search Screen\n`${searchInputValue}`"
            )
        }
    }
}

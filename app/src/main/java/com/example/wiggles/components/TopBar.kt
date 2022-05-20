package com.example.wiggles.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.wiggles.R
import com.example.wiggles.ui.theme.text
import com.example.wiggles.vm.ThemeViewModel

@Composable
fun TopBar(navController: NavHostController, iconsBar: Boolean, modifier: Modifier = Modifier, themeViewModel: ThemeViewModel = viewModel()) {
    var image by remember {
        mutableStateOf(false)
    }

    val theme: String by themeViewModel.theme.observeAsState("")
    if (!iconsBar) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.14f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(
                        text = "Hey Spikey",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Adopt a new friend near you!",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    IconButton(onClick = {
                        image = !image
                        themeViewModel.onThemeChanged(theme)
                    }) {
                        Icon(
                            painter = painterResource(id = if (image) R.drawable.on else R.drawable.off),
                            contentDescription = "",
                            modifier = Modifier
                                .size(24.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.padding(15.dp))
            Text(
                text = "Nearby results",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    } else {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .fillMaxHeight(0.1f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = Color.White,
                    modifier = modifier
                )
            }
            IconButton(onClick = {
                image = !image
            }) {
                Icon(
                    imageVector = if (image) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                    contentDescription = "",
                    tint = Color.White,
                    modifier = modifier
                )
            }
        }
    }
}

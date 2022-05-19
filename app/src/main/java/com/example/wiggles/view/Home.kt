package com.example.wiggles.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.wiggles.R
import com.example.wiggles.components.TopBar
import com.example.wiggles.data.FakeDogDatabase.dogList
import com.example.wiggles.model.Dog
import com.example.wiggles.navigation.Screen
import com.example.wiggles.ui.theme.*

@Composable
fun Home(navController: NavHostController) {
    WigglesTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            AppContent(navController)
        }
    }
}

@Composable
fun AppContent(navController: NavHostController) {
    Spacer(modifier = Modifier.padding(10.dp))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
    ) {
        LazyColumn {
            item {
                TopBar(navController, false)
            }
            items(count = dogList.size, key = {
                dogList[it].id
            }, itemContent = {
                dogList.forEach {
                    DogItem(
                        it,
                        onItemClicked = { dog ->
                            navController.navigate(
                                Screen.DetailScreen.withArgs(
                                    dog.id.toString(),
                                    dog.name,
                                    dog.location
                                )
                            )
                        }
                    )
                }
            })
        }
    }
}

@Composable
fun DogItem(
    dog: Dog,
    onItemClicked: (dog: Dog) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(top = 10.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(card)
            .clickable {
                onItemClicked(dog)
            },
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = dog.image),
                contentDescription = "",
                modifier = Modifier
                    .weight(0.8f)
                    .clip(RoundedCornerShape(15.dp))
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(10.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = dog.name,
                    style = MaterialTheme.typography.displayMedium,
                    color = text
                )
                Text(
                    text = buildString {
                        append(dog.age)
                        append("yrs | ")
                        append(dog.gender)
                    },
                    style = MaterialTheme.typography.displaySmall,
                    color = text
                )
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.pin),
                        contentDescription = "", tint = Color.Red
                    )
                    Text(
                        text = dog.location,
                        style = MaterialTheme.typography.displaySmall,
                        color = text,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .weight(0.8f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.End
            ) {
                Box(
                    modifier = Modifier
                        .width(65.dp)
                        .height(30.dp)
                        .clip(RoundedCornerShape(80.dp))
                        .background(if (dog.gender == "Male") miniCard else miniCardRed),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = dog.gender,
                        style = MaterialTheme.typography.displaySmall,
                        color = if (dog.gender == "Male") textCard else textCardRed
                    )
                }
                Text(
                    text = dog.location,
                    style = MaterialTheme.typography.displaySmall,
                    color = text
                )
            }
        }
    }
}

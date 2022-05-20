package com.example.wiggles.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wiggles.R
import com.example.wiggles.components.TopBar
import com.example.wiggles.data.FakeDogDatabase
import com.example.wiggles.model.Dog
import com.example.wiggles.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Detail(arguments: Bundle?, navController: NavHostController) {
    var id = ""
    var name = ""
    var location = ""
    arguments.let { data ->
        if (data != null) {
            id = data.getString("id").toString()
            name = data.getString("name").toString()
            location = data.getString("location").toString()
        }
    }

    val dog = FakeDogDatabase.dogList[id.toInt()]

    Scaffold(topBar = {
        TopBar(navController, true, modifier = Modifier.size(30.dp))
    }, content = {
        DetailsView(dog)
    })
}

@Composable
fun DetailsView(dog: Dog) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(bottom = 20.dp)
    ) {
        //image
        item {
            Image(
                painter = painterResource(id = dog.image), contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f),
                alignment = Alignment.CenterStart,
                contentScale = ContentScale.Crop
            )
        }
        //detail
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                    Text(
                        text = dog.name,
                        modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(verticalAlignment = Alignment.Bottom) {

                        val locationIcon: Painter =
                            painterResource(id = com.example.wiggles.R.drawable.pin)

                        Icon(
                            painter = locationIcon,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp, 16.dp),
                            tint = Color.Red
                        )

                        Text(
                            text = dog.location,
                            modifier = Modifier.padding(8.dp, 12.dp, 12.dp, 0.dp),
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.displaySmall
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "12 mins ago",
                        modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.displaySmall
                    )
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
                }
            }
        }
        //about
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Title(title = "My Story")
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = dog.about,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 0.dp, 16.dp, 0.dp),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Start
            )
        }
        //quik info
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Title(title = "Dog info")
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 0.dp, 16.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoCard(title = "Age", value = dog.age.toString().plus(" yrs"))
                InfoCard(title = "Color", value = dog.color)
                InfoCard(title = "Weight", value = dog.weight.toString().plus("Kg"))
            }
        }
        //owner info
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Title(title = "Dog info")
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = dog.owner.image),
                    contentDescription = "",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "David Berlin",
                        style = MaterialTheme.typography.displayMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Socialist & Developer",
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(
                    onClick = { }, modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(messenger)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.messanger),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
        //button
        item {
            Spacer(modifier = Modifier.height(36.dp))
            Button(
                onClick = { /* Do something! */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .padding(16.dp, 0.dp, 16.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = messenger,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("Adopt me")
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun InfoCard(title: String, value: String) {
    Box(
        modifier = Modifier
            .size(90.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = miniCard)
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.wrapContentWidth()
        ) {
            Text(
                text = value,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                color = Color.Gray,
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun Title(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 0.dp, 0.dp),
        color = text,
        style = MaterialTheme.typography.displaySmall,
        fontWeight = FontWeight.W600,
        textAlign = TextAlign.Start
    )
}
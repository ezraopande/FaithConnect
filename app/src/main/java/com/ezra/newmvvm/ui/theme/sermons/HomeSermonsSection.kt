package com.ezra.newmvvm.ui.theme.sermons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ezra.newmvvm.viewmodel.church.SermonViewModel


@Composable
fun SermonList(viewModel: SermonViewModel = viewModel(), navController: NavController) {
    val sermons by viewModel.sermons.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    // Fetch pastors' details for all sermons
    LaunchedEffect(sermons) {
        sermons.forEach { sermon ->
            viewModel.fetchPastor(sermon.pastor)
        }
    }

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        if (sermons.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "No sermon found", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))
            }
        } else {
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(sermons) { sermon ->
                    SermonCard(
                        navController = navController,
                        imageUrl = sermon.sermon_banner ?: "",
                        title = sermon.title,
                        id = sermon.id,
                        pastorName = viewModel.getPastorName(sermon.pastor),
                        modifier = Modifier.width(300.dp).height(200.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun SermonCard(
    navController: NavController,
    id: String,
    imageUrl: String,
    title: String,
    pastorName: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .height(200.dp) // Fixed height for full-width
            .fillMaxWidth()
            .clickable {
                navController.navigate("sermon_details/$id")
            },
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Image Background
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Overlay Text
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .background(Color.Black.copy(alpha = 0.5f))
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp
                    )
                )
                Text(
                    text = pastorName,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp
                    )
                )
            }
        }
    }
}




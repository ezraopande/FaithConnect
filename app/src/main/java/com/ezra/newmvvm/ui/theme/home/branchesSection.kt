package com.ezra.newmvvm.ui.theme.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.ezra.newmvvm.data.branches.Branch
import com.ezra.newmvvm.viewmodel.church.BranchViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BranchList(viewModel: BranchViewModel = viewModel()) {
    var branches by remember { mutableStateOf<List<Branch>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    // Fetch data when the Composable is first launched and every 20 seconds
    LaunchedEffect(Unit) {
        while (true) {
            isLoading = true
            coroutineScope.launch {
                viewModel.fetchBranches { fetchedBranches ->
                    branches = fetchedBranches
                    isLoading = false
                }
            }
            delay(190000)
        }
    }

    when {
        isLoading -> {
            // Show loading indicator while data is being fetched
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                CircularProgressIndicator()
                Text(text = "Updating Branches...")

            }
        }
        branches.isEmpty() -> {
            // Show "No branches found" message if the list is empty
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "No branches found",
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onSurface
                )
            }
        }
        else -> {
            // Display the list of branches
            LazyRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(branches) { branch ->
                    BranchCard(
                        imageUrl = branch.branch_image ?: "",
                        title = branch.name,
                        location = branch.location,
                       // isFavorite = false // Replace with actual favorite status if available
                    )
                }
            }
        }
    }
}





//fun BranchCard(imageUrl: String, title: String, location: String, isFavorite: Boolean) {
@Composable
fun BranchCard(imageUrl: String, title: String, location: String) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        elevation = 1.dp,
        modifier = Modifier
            .padding(8.dp)
            .width(150.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current).data(data = imageUrl).apply(block = fun ImageRequest.Builder.() {
                            crossfade(true)
                            transformations(CircleCropTransformation())
                        }).build()
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(120.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
//                if (isFavorite) {
//                    Box(
//                        modifier = Modifier
//                            .size(56.dp)
//                            .align(Alignment.TopEnd)
//                            .padding(8.dp)
//                            .border(2.dp, Color.White, CircleShape)
//                            .padding(8.dp)
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Favorite,
//                            contentDescription = null,
//                            tint = Color.Red,
//                            modifier = Modifier
//                                .size(40.dp)
//                        )
//                    }
//                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.h6.copy(fontSize = 16.sp, fontWeight = FontWeight.Bold)
            )
            Text(
                text = location,
                style = MaterialTheme.typography.body2.copy(color = Color.Gray)
            )
        }
    }
}

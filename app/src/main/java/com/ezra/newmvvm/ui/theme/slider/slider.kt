package com.ezra.newmvvm.ui.theme.slider

import SliderViewModel
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ezra.newmvvm.ui.theme.home.BottomBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SliderScreen(navController: NavController, viewModel: SliderViewModel = viewModel()) {
    val sliderList by viewModel.sliderList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Slides") },
                modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars),
            )
        },
        content = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(90.dp))
                if (isLoading) {
                    Text(
                        text = "Loading Sliders...",
                        textAlign = TextAlign.Center,
                        color = Color.Green

                    )

                } else {
//                    AutoScrollingImageSlider(sliderList)
                }
            }
        },
        bottomBar = { BottomBar(navController) }
    )
}

//@Composable
//fun AutoScrollingImageSlider(sliderList: List<SliderResponse>) {
//    var currentIndex by remember { mutableIntStateOf(0) }
//    val scope = rememberCoroutineScope()
//    val offsetX = remember { Animatable(0f) }
//
//    LaunchedEffect(currentIndex) {
//        offsetX.snapTo(1000f)  // Start outside the screen to the right
//        offsetX.animateTo(0f, animationSpec = tween(1000))  // Slide in from the right
//    }
//
//    LaunchedEffect(Unit) {
//        while (true) {
//            delay(90000)
//            scope.launch {
//                offsetX.animateTo(-1000f, animationSpec = tween(1000))  // Slide out to the left
//                currentIndex = (currentIndex + 1) % sliderList.size
//                offsetX.snapTo(1000f)  // Reset position to the right for the next image
//                offsetX.animateTo(0f, animationSpec = tween(1000))  // Slide in the new image
//            }
//        }
//    }
//
//    if (sliderList.isNotEmpty()) {
//        val imageUrl = sliderList[currentIndex].image
//        val painter = rememberAsyncImagePainter(model = imageUrl)
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .wrapContentHeight()
//                .padding(16.dp)
//                .offset { IntOffset(offsetX.value.toInt(), 0) }
//        ) {
//            Card(
//                shape = MaterialTheme.shapes.medium,
//                elevation = 8.dp,
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Image(
//                    painter = painter,
//                    contentDescription = null,
//                    modifier = Modifier.fillMaxWidth()
//                )
//            }
//        }
//
//
//    }
//}


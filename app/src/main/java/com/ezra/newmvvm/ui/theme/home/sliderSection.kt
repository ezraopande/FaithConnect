package com.ezra.newmvvm.ui.theme.home

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ezra.newmvvm.data.slider.SliderResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun AutoScrollingImageSlider(sliderList: List<SliderResponse>) {
    if (sliderList.isEmpty()) {
        // Handle the case where the list is empty
        Text(text = "No Slides Found", modifier = Modifier.padding(16.dp))
        return
    }

    var currentIndex by remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()
    val offsetX = remember { Animatable(0f) }

    LaunchedEffect(currentIndex) {
        scope.launch {
            offsetX.snapTo(1000f)
            offsetX.animateTo(0f, animationSpec = tween(1000))
        }
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            scope.launch {
                offsetX.animateTo(-1000f, animationSpec = tween(1000))  // Slide out to the left
                currentIndex = (currentIndex + 1) % sliderList.size
                offsetX.snapTo(1000f)  // Reset position to the right for the next image
                offsetX.animateTo(0f, animationSpec = tween(1000))  // Slide in the new image
            }
        }
    }

    if (sliderList.isNotEmpty()) {
        val imageUrl = sliderList[currentIndex].image
        val painter = rememberAsyncImagePainter(model = imageUrl)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp) // Set the desired height for the slider
                .padding(16.dp)
                .offset { IntOffset(offsetX.value.toInt(), 0) }
        ) {
            Card(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                modifier = Modifier.fillMaxSize() // Ensure the Card fills the Box
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize() // Ensure the Image fills the Card
                )
            }
        }
    }
}


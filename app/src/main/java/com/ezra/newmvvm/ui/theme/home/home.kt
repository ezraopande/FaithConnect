package com.ezra.newmvvm.ui.theme.home

import SliderViewModel
import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.ezra.newmvvm.navigation.ROUTE_HOME
import com.ezra.newmvvm.navigation.ROUTE_LOGIN
import com.ezra.newmvvm.navigation.ROUTE_PRODUCTS
import com.ezra.newmvvm.ui.theme.sermons.SermonList
import com.ezra.newmvvm.viewmodel.church.SermonViewModel

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import kotlin.text.Typography.dagger


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navController: NavHostController,
    sliderViewModel: SliderViewModel = viewModel()
) {
    val sliderList by sliderViewModel.sliderList.collectAsState()

    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFFFFFF))
                    .windowInsetsPadding(WindowInsets.systemBars)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GreetingSection()
                SearchBar()
                AutoScrollingImageSlider(sliderList = sliderList)
                BranchList()
                SermonList(navController = navController)

                Spacer(modifier = Modifier.height(70.dp))
            }
        },
        bottomBar = {
            BottomBar(navController)
        }
    )
}


@Composable
fun BottomBar(navController: NavController) {
    val selectedIndex = remember { mutableIntStateOf(0) }
    val animatedSize = animateDpAsState(
        targetValue = if (selectedIndex.intValue == 1) 60.dp else 50.dp, label = ""
    )

    Box {
        BottomNavigation(
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.systemBars)
                .background(Color(0xFF5DD59C)),
            elevation = 10.dp,
            backgroundColor = Color(0xFF5DD59C)
        ) {
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null,
                        tint = Color.White

                    )
                },
                label = { Text(text = "Home", color = Color.White) },
                selected = (selectedIndex.intValue == 0),
                onClick = {
                    selectedIndex.intValue = 0
                    navController.navigate(ROUTE_HOME)
                })

            BottomNavigationItem(
                icon = { Box(Modifier.size(animatedSize.value)) },
                label = {},
                selected = false,
                onClick = {})

            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = Color.White
                    )
                },
                label = { Text(text = "Account", color = Color.White) },
                selected = (selectedIndex.intValue == 2),
                onClick = {
                    selectedIndex.intValue = 2
                    navController.navigate(ROUTE_LOGIN)
                })
        }

        FloatingActionButton(
            onClick = {
                selectedIndex.intValue = 1
                navController.navigate(ROUTE_PRODUCTS)
            },
            backgroundColor = Color.White,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-49).dp)
                .clip(CircleShape)
                .size(70.dp)
                .border(2.dp, Color(0xFFAEE1C9), CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = null,
                tint = Color(0xFF5DD59C)
            )
        }
    }
}



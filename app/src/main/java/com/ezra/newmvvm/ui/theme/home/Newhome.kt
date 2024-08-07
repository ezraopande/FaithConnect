package com.ezra.newmvvm.ui.theme.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.vector.ImageVector
import com.ezra.newmvvm.R



//@Composable
//fun ChurchCard(imageRes: Int, title: String, location: String, isFavorite: Boolean) {
//    Surface(
//        shape = RoundedCornerShape(16.dp),
//        elevation = 4.dp,
//        modifier = Modifier
//            .padding(8.dp)
//            .width(150.dp)
//    ) {
//        Column(
//            modifier = Modifier.padding(8.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Box {
//                Image(
//                    painter = painterResource(id = imageRes),
//                    contentDescription = null,
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .height(120.dp)
//                        .clip(RoundedCornerShape(16.dp))
//                )
//                if (isFavorite) {
//                    Icon(
//                        imageVector = Icons.Default.Favorite,
//                        contentDescription = null,
//                        tint = Color.Red,
//                        modifier = Modifier
//                            .size(24.dp)
//                            .align(Alignment.TopEnd)
//                            .padding(8.dp)
//                    )
//                }
//            }
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = title,
//                style = MaterialTheme.typography.h6.copy(fontSize = 16.sp, fontWeight = FontWeight.Bold)
//            )
//            Text(
//                text = location,
//                style = MaterialTheme.typography.body2.copy(color = Color.Gray)
//            )
//        }
//    }
//}

//@Composable
//fun ChurchList() {
//    Row(
//        horizontalArrangement = Arrangement.spacedBy(16.dp),
//        modifier = Modifier.padding(16.dp)
//    ) {
//        ChurchCard(
//            imageRes = R.drawable.logo, // Replace with your image resource
//            title = "All Saints Church",
//            location = "Jakarta",
//            isFavorite = true
//        )
//        ChurchCard(
//            imageRes = R.drawable.logo, // Replace with your image resource
//            title = "Santa Basilica",
//            location = "Jakarta",
//            isFavorite = false
//        )
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun PreviewChurchList() {
//    ChurchList()
//}

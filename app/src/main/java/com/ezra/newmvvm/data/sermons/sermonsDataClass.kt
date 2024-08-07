package com.ezra.newmvvm.data.sermons

data class SermonResponse(
    val id: String,
    val title: String,
    val sermon_banner: String?,
    val sermon_youtube_link: String,
    val about_sermon: String,
    val pastor: String // ID of the pastor
)

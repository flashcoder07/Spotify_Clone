package com.example.spotify

data class MusicInfo(
    val `data`: List<Data>,
    val next: String,
    val total: Int
)
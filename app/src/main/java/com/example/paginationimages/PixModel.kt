package com.example.paginationimages

data class PixModel(
    val hits : List<ImageModel>
)

data class ImageModel(
    val largeImageURL:  String
)

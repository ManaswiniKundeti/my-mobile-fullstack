package com.example.myfullstackapp.data

data class CardDto(
    val title: String,
    val subtitle: String
)

data class HomeExperienceDto(
    val greeting: String,
    val cards: List<CardDto>
)

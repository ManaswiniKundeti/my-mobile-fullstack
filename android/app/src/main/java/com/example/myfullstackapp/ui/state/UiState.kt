package com.example.myfullstackapp.ui.state

import com.example.myfullstackapp.data.HomeExperienceDto

sealed class UiState {
    object Loading : UiState()
    data class Success(val data: HomeExperienceDto) : UiState()
    data class Error(val message: String) : UiState()
}
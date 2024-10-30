package com.eventapp.mealswithroom.ui.categories.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.eventapp.mealswithroom.database.categories.MealCategoryEntity
import com.eventapp.mealswithroom.ui.categories.repositories.MealsCategoryRepository
import kotlinx.coroutines.launch
import okio.IOException

class MealsCategoriesViewModel(private val repository: MealsCategoryRepository) : ViewModel() {

    // Cambiar LiveData por MutableState para Compose
    var categories = mutableStateOf<List<MealCategoryEntity>>(emptyList())
        private set

    var isLoading = mutableStateOf(false)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    fun fetchCategories() {
        isLoading.value = true
        errorMessage.value = null
        viewModelScope.launch {
            try {
                categories.value = repository.getMealsCategories()
            } catch (e: Exception) {
                errorMessage.value = when (e) {
                    is IOException -> "Network error: Check your internet connection."
                    else -> "An unexpected error occurred."
                }
            } finally {
                isLoading.value = false
            }
        }
    }
}

class MealViewModelFactory(private val repository: MealsCategoryRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MealsCategoriesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MealsCategoriesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

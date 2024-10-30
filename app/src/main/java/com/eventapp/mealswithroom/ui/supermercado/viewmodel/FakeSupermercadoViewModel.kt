package com.eventapp.mealswithroom.ui.supermercado.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.eventapp.mealswithroom.database.supermercado.SupermercadoEntity

class FakeSupermercadoViewModel : ViewModel() {
    // Datos de ejemplo para el Preview
    val items = mutableStateOf(
        listOf(
            SupermercadoEntity(id = "1", itemName = "Manzanas", quantity = 3, imagePath = null),
            SupermercadoEntity(id = "2", itemName = "Pan", quantity = 1, imagePath = null),
            SupermercadoEntity(id = "3", itemName = "Leche", quantity = 2, imagePath = null)
        )
    )

    // No hace nada en este ViewModel simulado
    fun loadItems() { /* No se necesita implementación */ }
}

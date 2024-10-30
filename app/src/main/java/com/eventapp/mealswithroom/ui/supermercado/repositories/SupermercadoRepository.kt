package com.eventapp.mealswithroom.ui.supermercado.repositories

import com.eventapp.mealswithroom.database.supermercado.SupermercadoDao
import com.eventapp.mealswithroom.database.supermercado.SupermercadoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SupermercadoRepository(private val supermercadoDao: SupermercadoDao) {

    // Obtener todos los artículos del supermercado
    suspend fun getAllItems(): List<SupermercadoEntity> {
        return withContext(Dispatchers.IO) {
            supermercadoDao.getAllMealCategories()
        }
    }

    // Guardar la ruta de la imagen en la entidad correspondiente
    suspend fun saveImagePath(itemId: String, imagePath: String) {
        withContext(Dispatchers.IO) {
            val item = supermercadoDao.getItemById(itemId)
            if (item != null) {
                val updatedItem = item.copy(imagePath = imagePath)
                supermercadoDao.update(updatedItem)
            }
        }
    }
}

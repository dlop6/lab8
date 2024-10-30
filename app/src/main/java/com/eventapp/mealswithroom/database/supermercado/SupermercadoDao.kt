package com.eventapp.mealswithroom.database.supermercado

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface SupermercadoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<SupermercadoEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: SupermercadoEntity)

    @Update
    suspend fun update(item: SupermercadoEntity)

    @Delete
    suspend fun delete(item: SupermercadoEntity)

    @Query("SELECT * FROM supermercado_meals")
    suspend fun getAllMealCategories(): List<SupermercadoEntity>


    @Query("SELECT * FROM supermercado_meals WHERE id = :itemId")
    suspend fun getItemById(itemId: String): SupermercadoEntity?
}

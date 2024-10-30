package com.eventapp.mealswithroom.database.supermercado

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "supermercado_meals")
data class SupermercadoEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,

    @ColumnInfo(name = "itemName") val itemName: String,
    @ColumnInfo(name = "quantity") val quantity: Int,
    @ColumnInfo(name = "imagePath") val imagePath: String? = null
)

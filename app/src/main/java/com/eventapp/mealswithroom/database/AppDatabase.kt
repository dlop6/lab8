import androidx.room.Database
import androidx.room.RoomDatabase
import com.eventapp.mealswithroom.database.categories.MealCategoryDao
import com.eventapp.mealswithroom.database.categories.MealCategoryEntity
import com.eventapp.mealswithroom.database.supermercado.SupermercadoDao
import com.eventapp.mealswithroom.database.supermercado.SupermercadoEntity

@Database(entities = [MealCategoryEntity::class, SupermercadoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun mealCategoryDao(): MealCategoryDao
    abstract fun supermercadoDao(): SupermercadoDao
}

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eventapp.mealswithroom.database.supermercado.SupermercadoEntity
import com.eventapp.mealswithroom.ui.supermercado.repositories.SupermercadoRepository
import kotlinx.coroutines.launch

class SupermercadoViewModel(private val repository: SupermercadoRepository) : ViewModel() {

    // Estado observable para la lista de artículos
    var items = mutableStateOf<List<SupermercadoEntity>>(emptyList())
        private set

    // Cargar los artículos desde el repository
    fun loadItems() {
        viewModelScope.launch {
            items.value = repository.getAllItems()
        }
    }

    // Guardar la ruta de la imagen a través del repository
    fun saveImagePath(itemId: String, imagePath: String) {
        viewModelScope.launch {
            repository.saveImagePath(itemId, imagePath)
            loadItems() // Recargar los artículos para reflejar la actualización
        }
    }
}

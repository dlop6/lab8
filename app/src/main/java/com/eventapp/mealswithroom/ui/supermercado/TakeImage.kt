import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.eventapp.mealswithroom.database.supermercado.SupermercadoEntity
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale // Asegúrate de importar Locale

@Composable
fun TakeImage(viewModel: SupermercadoViewModel, itemId: String) {
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    // Configura el ActivityResultLauncher para capturar una imagen
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            imageUri?.let { uri ->
                // Pasa el itemId y la ruta de la imagen al ViewModel
                viewModel.saveImagePath(itemId, uri.toString())
            }
        }
    }

    // Función para crear el archivo de imagen y capturar la imagen
    fun captureImage() {
        val file = createImageFile(context)
        imageUri = Uri.fromFile(file)
        // Verifica que imageUri no sea null antes de lanzar el intent
        imageUri?.let { uri ->
            launcher.launch(uri)
        }
    }

    // Botón para tomar la foto
    Button(onClick = { captureImage() }) {
        Text("Tomar Foto")
    }
}

// Crea un archivo de imagen en el almacenamiento externo
fun createImageFile(context: Context): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile("IMG_${timeStamp}_", ".jpg", storageDir)
}

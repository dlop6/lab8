import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eventapp.mealswithroom.database.supermercado.SupermercadoEntity
import com.eventapp.mealswithroom.ui.supermercado.viewmodel.FakeSupermercadoViewModel

@Composable
fun SupermarketScreen(viewModel: FakeSupermercadoViewModel = viewModel()) {
    val items by viewModel.items

    LaunchedEffect(Unit) {
        viewModel.loadItems()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /* TODO: Agregar funcionalidad más adelante */ }) {
                Icon(Icons.Filled.Add, contentDescription = "Agregar Artículo")
            }
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items) { item ->
                    SupermarketItemCard(item)
                }
            }
        }
    )
}

@Composable
fun SupermarketItemCard(item: SupermercadoEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Espacio reservado para la imagen
            Box(
                modifier = Modifier
                    .size(64.dp)  // Tamaño de la "imagen"
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "Imagen",
                    color = Color.DarkGray,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Column para el nombre del artículo y la cantidad
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = item.itemName,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Cantidad: ${item.quantity}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SupermarketScreenPreview() {
    SupermarketScreen(viewModel = FakeSupermercadoViewModel())
}

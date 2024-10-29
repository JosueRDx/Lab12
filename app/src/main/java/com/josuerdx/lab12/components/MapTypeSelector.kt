package com.josuerdx.lab12.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.maps.android.compose.MapType

@Composable
fun MapTypeSelector(onMapTypeChange: (MapType) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    val mapTypeState = remember { mutableStateOf(MapType.NORMAL) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, end = 20.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        Button(onClick = { showDialog = true }) {
            Text("Tipo de mapa")
        }

        // Cuadro de opciones
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Selecciona el tipo de mapa", fontSize = 18.sp)
                    }
                },
                text = {
                    // Opciones
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = {
                                mapTypeState.value = MapType.NORMAL
                                onMapTypeChange(MapType.NORMAL)
                                showDialog = false
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Text("Normal", maxLines = 1)
                        }
                        Button(
                            onClick = {
                                mapTypeState.value = MapType.HYBRID
                                onMapTypeChange(MapType.HYBRID)
                                showDialog = false
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Text("Híbrido", maxLines = 1)
                        }
                        Button(
                            onClick = {
                                mapTypeState.value = MapType.SATELLITE
                                onMapTypeChange(MapType.SATELLITE)
                                showDialog = false
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Text("Satélite", maxLines = 1)
                        }
                        Button(
                            onClick = {
                                mapTypeState.value = MapType.TERRAIN
                                onMapTypeChange(MapType.TERRAIN)
                                showDialog = false
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Text("Terreno", maxLines = 1)
                        }
                    }
                },
                confirmButton = {
                    // Botón de cerrar
                    Button(
                        onClick = { showDialog = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Cerrar", maxLines = 1)
                    }
                }
            )
        }
    }
}

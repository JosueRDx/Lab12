package com.josuerdx.lab12.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
import com.josuerdx.lab12.R

@Composable
fun MapScreen() {
    val ArequipaLocation = LatLng(-16.4057, -71.5401) // Arequipa, Perú
    val cameraPositionState = rememberCameraPositionState {
        position = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(ArequipaLocation, 12f)
    }

    // Lista de ubicaciones adicionales (puntos de interés)
    val locations = listOf(
        LatLng(-16.4292, -71.5238), // JLByR
        LatLng(-16.4160, -71.4824), // Paucarpata
        LatLng(-16.3537, -71.5628)  // Zamacola
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // Añadir GoogleMap al layout
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            // Añadir marcador en Arequipa
            Marker(
                state = rememberMarkerState(position = ArequipaLocation),
                icon = BitmapDescriptorFactory.fromResource(R.drawable.montana1), // icono personalizado
                title = "Arequipa, Perú"
            )

            // Añadir marcadores en las otras ubicaciones
            locations.forEach { location ->
                Marker(
                    state = rememberMarkerState(position = location),
                    title = "Punto de interés",
                    snippet = "Ubicación"
                )
            }
        }
    }
}

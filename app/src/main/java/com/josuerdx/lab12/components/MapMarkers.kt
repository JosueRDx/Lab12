package com.josuerdx.lab12.components

import androidx.compose.runtime.Composable
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberMarkerState
import com.josuerdx.lab12.R

@Composable
fun MapMarkers(ArequipaLocation: LatLng) {
    // Lista de ubicaciones adicionales (puntos de interés)
    val locations = listOf(
        LatLng(-16.4292, -71.5238), // JLByR
        LatLng(-16.4160, -71.4824), // Paucarpata
        LatLng(-16.3537, -71.5628),  // Zamacola
        LatLng(-16.2521, -71.6837)  // Yura
    )

    // Añadir marcador principal en Arequipa
    Marker(
        state = rememberMarkerState(position = ArequipaLocation),
        icon = BitmapDescriptorFactory.fromResource(R.drawable.montana1),
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
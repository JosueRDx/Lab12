package com.josuerdx.lab12.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.josuerdx.lab12.components.MapMarkers
import com.josuerdx.lab12.components.MapPolygons
import com.josuerdx.lab12.components.MapPolylines

@Composable
fun MapScreen() {
    val ArequipaLocation = LatLng(-16.4057, -71.5401) // Arequipa, Perú
    val cameraPositionState = rememberCameraPositionState {
        position = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(ArequipaLocation, 12f)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Añadir GoogleMap al layout
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            // Añadir marcadores
            MapMarkers(ArequipaLocation)

            // Dibujar polígonos
            MapPolygons()

            // Dibujar polilíneas
            MapPolylines()
        }
    }

    // Controlar la cámara programáticamente
    LaunchedEffect(Unit) {
        cameraPositionState.animate(
            update = CameraUpdateFactory.newLatLngZoom(LatLng(-16.2520984, -71.6836503), 12f), // Yura
            durationMs = 3000
        )
    }
}

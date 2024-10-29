package com.josuerdx.lab12.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.rememberCameraPositionState
import com.josuerdx.lab12.components.MapMarkers
import com.josuerdx.lab12.components.MapPolygons
import com.josuerdx.lab12.components.MapPolylines
import com.josuerdx.lab12.components.MapTypeSelector

@Composable
fun MapScreen() {
    val ArequipaLocation = LatLng(-16.4057, -71.5401) // Arequipa, Perú
    val cameraPositionState = rememberCameraPositionState {
        position = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(ArequipaLocation, 12f)
    }

    // Estado para el tipo de mapa
    val mapTypeState = remember { mutableStateOf(MapType.NORMAL) }

    val mapProperties = MapProperties(mapType = mapTypeState.value)

    Box(modifier = Modifier.fillMaxSize()) {
        // Añadir GoogleMap al layout con las propiedades del mapa
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = mapProperties
        ) {
            // Añadir marcadores
            MapMarkers(ArequipaLocation)

            // Dibujar polígonos
            MapPolygons()

            // Dibujar polilíneas
            MapPolylines()
        }

        // Selector de tipo de mapa
        MapTypeSelector(onMapTypeChange = { newType ->
            mapTypeState.value = newType // Actualiza el tipo de mapa
        })
    }

    // Controlar la cámara programáticamente
    LaunchedEffect(Unit) {
        cameraPositionState.animate(
            update = CameraUpdateFactory.newLatLngZoom(LatLng(-16.2520984, -71.6836503), 12f), // Yura
            durationMs = 3000
        )
    }
}

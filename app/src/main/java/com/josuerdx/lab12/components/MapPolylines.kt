package com.josuerdx.lab12.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.model.Dash
import com.google.android.gms.maps.model.Gap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PatternItem
import com.google.maps.android.compose.Polyline

@Composable
fun MapPolylines() {
    // Ejemplo 1
    val simplePolyline = listOf(
        LatLng(-16.4057, -71.5401), // Arequipa
        LatLng(-16.2521, -71.6837)  // Yura
    )

    Polyline(
        points = simplePolyline,
        color = Color.Blue,
        width = 5f
    )

    // Ejemplo 2
    val threePointPolyline = listOf(
        LatLng(-16.4057, -71.5401), // Arequipa
        LatLng(-16.4160, -71.4824), // Paucarpata
        LatLng(-16.3537, -71.5628)  // Zamacola
    )

    Polyline(
        points = threePointPolyline,
        color = Color.Green,
        width = 4f
    )

    // Ejemplo 3
    val dashedPolyline = listOf(
        LatLng(-16.4057, -71.5401), // Arequipa
        LatLng(-16.4292, -71.5238), // JLByR
        LatLng(-16.4160, -71.4824), // Paucarpata
        LatLng(-16.3537, -71.5628)  // Zamacola
    )

    val pattern: List<PatternItem> = listOf(Dash(30f), Gap(20f))

    Polyline(
        points = dashedPolyline,
        color = Color.Magenta,
        width = 6f,
        pattern = pattern
    )
}
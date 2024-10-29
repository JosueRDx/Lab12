package com.josuerdx.lab12.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.josuerdx.lab12.components.MapMarkers
import com.josuerdx.lab12.components.MapPolygons
import com.josuerdx.lab12.components.MapPolylines
import com.josuerdx.lab12.components.MapTypeSelector

@Composable
fun MapScreen() {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val cameraPositionState = rememberCameraPositionState {
        position = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(LatLng(-16.4057, -71.5401), 12f)
    }

    // Estado para el tipo de mapa
    val mapTypeState = remember { mutableStateOf(MapType.NORMAL) }
    val mapProperties = MapProperties(mapType = mapTypeState.value)

    // Estado para almacenar la ubicación del usuario
    var userLocation by remember { mutableStateOf<LatLng?>(null) }

    // Estado y lanzador para solicitar el permiso de ubicación
    var hasLocationPermission by remember { mutableStateOf(false) }
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        hasLocationPermission = isGranted
    }

    // Solicitar permiso si no está concedido
    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            hasLocationPermission = true
        }
    }

    // Obtener la ubicación actual si se tiene permiso
    LaunchedEffect(hasLocationPermission) {
        if (hasLocationPermission) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    userLocation = LatLng(it.latitude, it.longitude)
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Añadir GoogleMap al layout con las propiedades del mapa
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = mapProperties
        ) {
            // Añadir marcador en la ubicación del usuario
            userLocation?.let { location ->
                Marker(
                    state = rememberMarkerState(position = location),
                    title = "Tu ubicación actual"
                )
            }

            /*
            // Añadir marcadores
            MapMarkers(LatLng(-16.4057, -71.5401))

            // Dibujar polígonos
            MapPolygons()

            // Dibujar polilíneas
            MapPolylines()
            */
        }

        // Selector de tipo de mapa
        MapTypeSelector(onMapTypeChange = { newType ->
            mapTypeState.value = newType // Actualiza el tipo de mapa
        })
    }

    // Controlar la cámara programáticamente
    LaunchedEffect(userLocation) {
        userLocation?.let { location ->
            cameraPositionState.animate(
                update = CameraUpdateFactory.newLatLngZoom(location, 20f),
                durationMs = 3000
            )
        }
    }

    /*
    LaunchedEffect(Unit) {
        cameraPositionState.animate(
            update = CameraUpdateFactory.newLatLngZoom(LatLng(-16.2520984, -71.6836503), 12f), // Yura
            durationMs = 3000
        )
    }
    */
}
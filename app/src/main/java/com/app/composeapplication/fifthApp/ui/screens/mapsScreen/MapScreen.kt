package com.app.composeapplication.fifthApp.ui.screens.mapsScreen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ToggleOff
import androidx.compose.material.icons.filled.ToggleOn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.composeapplication.R
import com.app.composeapplication.fourthApp.launchedEffect.ui.theme.ComposeApplicationTheme
import com.google.android.gms.maps.model.*
import com.google.maps.android.compose.*

@Composable
fun MapScreen(

    viewmModel: MapViewmModel = hiltViewModel(),
) {
    //https://snazzymaps.com/style/87718/fallout-pip-boy

    val chd = LatLng(30.740736654, 76.7605539411)
    val cameraPositionState = rememberCameraPositionState {
        position= CameraPosition.fromLatLngZoom(chd,12f)
    }
    //val cameraPositionState1 =
    var properties by remember {
                   mutableStateOf(viewmModel.state.properties.copy(
                       mapType = MapType.NORMAL
                   ))
    }
    properties =  viewmModel.state.properties
    var circleCenter by remember { mutableStateOf(chd) }
    val scaffoldState = rememberScaffoldState()
      val uiSettngs = remember {
         MapUiSettings(zoomControlsEnabled = true, compassEnabled = false )
     }
    Scaffold( Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewmModel.mapEvents(MapEvent.ToggleFallout)
            }) {
                Icon(imageVector =
                if (viewmModel.state.isTogglemap) {
                    Icons.Default.ToggleOn
                } else (Icons.Default.ToggleOff), contentDescription = "Toggle Fallout Map")
            }
        }
    ) {
        it
        GoogleMap( cameraPositionState = cameraPositionState,
            uiSettings =uiSettngs ,
            modifier = Modifier.fillMaxSize(),
            properties = properties, //viewmModel.state.properties,
            onMapLongClick = {
                viewmModel.mapEvents(MapEvent.OnMapLongClick(it))
            }
        ){
               viewmModel.state.parkingSpot.forEach { spot->



                   MarkerInfoWindowContent(
                   state= MarkerState(position = LatLng(spot.lat, spot.longgt)),
                  title = "Parking Spot (${spot.lat},${spot.longgt})",
                  snippet = "Long Click to delete",
                  onInfoWindowLongClick = {
                      viewmModel.mapEvents(
                          MapEvent.OnInfoWindowLongClick(spot))
                  }
                 ,     onClick = {
                          it.showInfoWindow()
                          false
                      }
                  , icon = BitmapDescriptorFactory.defaultMarker(
                          BitmapDescriptorFactory.HUE_GREEN
                  )
                  )
                   Circle(center = circleCenter, fillColor = colorResource(id = R.color.light_),
                       strokeColor = MaterialTheme.colors.secondaryVariant,
                       radius = 1000.0)
               }
        }

        Column() {
            MapTypeControlls( onMapTypeClick = {
                properties = viewmModel.state.properties.copy(
                  mapType = it
              )

             //   Log.e("TAG", "MapScreen: ${viewmModel.state. properties.mapType.name}", )
              //  viewmModel.mapEvents(MapEvent.refreshFallout)
            })
        }

    }

}


@Composable
fun MapTypeControlls( onMapTypeClick:(MapType)->Unit) {
MapType.values().forEach {
    Button(
        modifier = Modifier.padding(4.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.onPrimary,
            contentColor = MaterialTheme.colors.primary
        ),
        onClick = {onMapTypeClick(it)}
    ) {
        Text(text = it.name, style = MaterialTheme.typography.body1)
    }
}

}
@Preview
@Composable
fun priviewe() {
ComposeApplicationTheme {
//    GoogleMap()
    MapTypeControlls( onMapTypeClick = {})
}
}
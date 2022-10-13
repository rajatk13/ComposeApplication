package com.app.composeapplication.fifthApp.ui.screens.mapsScreen



import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.composeapplication.fifthApp.ui.screens.mapsScreen.reppository.MapRepository
import com.app.composeapplication.fifthApp.ui.screens.mapsScreen.reppository.MapRepositoryImpl
import com.google.android.gms.maps.model.MapStyleOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewmModel @Inject constructor(
    private  val repository: MapRepository
) : ViewModel() {
    var state  by mutableStateOf(MapState())

     init {
         viewModelScope.launch {
             repository.getParkingSpot().collectLatest { spots->
                state = state.copy(
                 parkingSpot = spots
                )

             }
         }
     }

    fun mapEvents(events:MapEvent){
        when(events){
            is MapEvent.refreshFallout->{
                state = state
            }
            is MapEvent.ToggleFallout->{
                 state = state.copy(
                 properties =
                      state.properties.copy(
                         mapStyleOptions = if(state.isTogglemap) null
                             else  MapStyleOptions(MapStyles.json)
                     ),
                     isTogglemap = !state.isTogglemap
                 )
            }
            is MapEvent.OnMapLongClick->{
                viewModelScope.launch {
                    repository.insertParkingSpot(ParkingSpot(
                        events.latLng.latitude,
                        events.latLng.longitude

                    ))
                }
            }
            is MapEvent.OnInfoWindowLongClick->{
                viewModelScope.launch {
                    repository.deleteParkingSpot(events.spot)
                }
            }
        }
    }
}
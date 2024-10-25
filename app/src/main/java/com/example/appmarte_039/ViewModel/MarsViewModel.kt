package com.example.appmarte_039.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appmarte_039.Model.Local.MarsDatabase
import com.example.appmarte_039.Model.MarsRepository
import com.example.appmarte_039.Model.Remote.MarsRealState
import kotlinx.coroutines.launch

class MarsViewModel(application: Application): AndroidViewModel(application) {

    //1 - Comunicar con el repository

    private val repository : MarsRepository

    // representa los terrenos de marte la respuesta de la Api

    lateinit var  liveDatafromInternet : LiveData<List<MarsRealState>>

    // para mostrar lo que estamos de recibiendo
    val allMars : LiveData<List<MarsRealState>>


    init {
        val MarsDao = MarsDatabase.getDataBase(application).marsDao()
        repository = MarsRepository(MarsDao)

        viewModelScope.launch {
           // ACA SE HACE LA INSERCIÓN
            repository.fechDataFromInternetCoroutines()
        }
        allMars = repository.listAllTerrains
        liveDatafromInternet = repository.datafromInternet

        // para testear NO SE ACTUALIZA   aca no se puede observar . hay que observar desde la vista

        liveDatafromInternet.observeForever { data ->
            Log.d("MarsViewModel", "data Received in View Model : $data")

        }

}


// funcion para seleccionar

private var selectedMarsTerrains: MutableLiveData<MarsRealState> = MutableLiveData()

// creamos esta función para seleccionar

fun seleted (mars:MarsRealState)
{

selectedMarsTerrains.value = mars
}


fun seletedItem():LiveData<MarsRealState> = selectedMarsTerrains


// INSERT
fun insertMars(mars:MarsRealState) =  viewModelScope.launch {
repository.insertMars(mars)
}

// UPDATE

fun updateMars(mars:MarsRealState) =  viewModelScope.launch {
repository.updateMars(mars)
}

// GET FOR ID

fun getMarsById(id:Int) :LiveData<MarsRealState>{
return  repository.getMarsById(id)
}
}








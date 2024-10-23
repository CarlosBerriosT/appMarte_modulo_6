package com.example.appmarte_039.Model.Remote

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface MarsApi {

    // hace las solicitudes a la Api y va tener los endpoints


   @GET("realestate")
    fun fetchMarsData(): Call<List<MarsRealState>> // vieja confiable



    //se trabaja con corrutinas
    @GET("realestate")
    suspend fun  fetchMarsDataCoroutines():Response<List<MarsRealState>>
}
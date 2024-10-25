package com.example.appmarte_039.Model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.appmarte_039.Model.Local.MarsDao
import com.example.appmarte_039.Model.Remote.MarsRealState
import com.example.appmarte_039.Model.Remote.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class MarsRepository ( private val marsDao : MarsDao) {



    //**************************Tiene que ver con la api MarsApi
    // llamar al metodo de conexión
    private val retrofitClient = RetrofitClient.getRetrofit()

    // hace referencia al pojo y la respuesta que vamos a a recibir
     val datafromInternet  = MutableLiveData<List<MarsRealState>>()



    // Vieja confiable

    fun fetchDataMars(): LiveData<List<MarsRealState>> {

        Log.d("Repo", "VIEJA CONFIABLE")
        retrofitClient.fetchMarsData().enqueue(object : retrofit2.Callback<List<MarsRealState>> {
            override fun onResponse(
                call: Call<List<MarsRealState>>,
                response: Response<List<MarsRealState>>
            ) {


                when (response.code()) {
                    in 200..299 -> datafromInternet.value = response.body()

                    in 300..399 -> Log.d("REPO", " ${response.code()} ---${response.errorBody()}")
                    else ->
                        Log.d("REPO", " ${response.code()} ---${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<List<MarsRealState>>, t: Throwable) {
                Log.e("REPO", "${t.message}")
            }


        })

        return datafromInternet


    }


         // nueva forma con corrutinas parte 2


        suspend fun  fechDataFromInternetCoroutines (){

            try {
                val response = retrofitClient.fetchMarsDataCoroutines()
                when (response.code()) {
                    in 200..299 -> response?.body().let {

                        if (it != null) {

                            marsDao.insertAllMars(it)
                            Log.d("TERRENOS", "$it")
                        }
                    }

                    in 300..399 -> Log.d("REPO", " ${response.code()} ---${response.errorBody()}")
                    else ->
                        Log.d("REPO", " ${response.code()} ---${response.errorBody()}")
                }

            }catch ( t: Throwable){

                Log.d("Repo", "${t.message}")
            }

        }



    //******************** FUNCIONES DEL DAO CRUD*********************

    // traer elemento por type
    fun getMarsByType(type: String) : LiveData<MarsRealState>{
        return  getMarsByType(type)
    }


    // traer por elemento por id
    fun getMarsById(id: Int) : LiveData<MarsRealState>{
        return  getMarsById(id)
    }

    // val  tiene con  modelo local
     val listAllTerrains : LiveData<List<MarsRealState>> = marsDao.getAllMars()


     //INSERT

     suspend fun  insertMars(mars: MarsRealState){
         marsDao.insertMars(mars)
     }


    // UPDATE


    suspend fun  updateMars(mars: MarsRealState){
        marsDao.updateMars(mars)
    }

  // DELETE
    suspend fun  deleteAll(){
        marsDao.deleteAll()
    }


    // Get Id
    fun getTerrainsById(id:Int) :LiveData<MarsRealState>{
        return marsDao.getMarsById(id)
    }





}
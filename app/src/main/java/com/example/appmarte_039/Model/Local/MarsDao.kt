package com.example.appmarte_039.Model.Local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.appmarte_039.Model.Remote.MarsRealState


@Dao
interface MarsDao {

// estaba recibiendo marsdatabase
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMars(mars: MarsRealState)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMars(mars: List<MarsRealState>)


    @Update
    suspend fun updateMars(mars: MarsRealState)

    @Delete
    suspend fun deleteTask(mars: MarsRealState)


    @Query("DELETE FROM mars_table")
    suspend fun deleteAll()


    // trae todos los terrenos

    @Query("SELECT * FROM mars_table ORDER BY id DESC")
    fun getAllMars(): LiveData<List<MarsRealState>>

    // filtrar por tipo

    @Query("SELECT * FROM mars_table WHERE type = :type LIMIT 1 ")
    fun getMarsByType(type: String): LiveData<MarsRealState>

    // filtrar por id
    // correjir  id es un Int
    @Query("SELECT * FROM mars_table WHERE id = :id")
    fun getMarsById(id: Int): LiveData<MarsRealState>


}
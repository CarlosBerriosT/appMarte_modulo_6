package com.example.appMarte_6.Model.Local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.appMarte_6.Model.Remote.MarsRealState


@Dao
interface MarsDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMars(mars: MarsRealState)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMars(mars: List<MarsRealState>)


    @Update
    suspend fun updateMars(mars: MarsRealState)

    @Delete
    suspend fun deleteMars(mars: MarsRealState)


    @Query("DELETE FROM mars_table")
    suspend fun deleteAll()




    @Query("SELECT * FROM mars_table ORDER BY id DESC")
    fun getAllMars(): LiveData<List<MarsRealState>>



    @Query("SELECT * FROM mars_table WHERE type = :type LIMIT 1 ")
    fun getMarsByType(type: String): LiveData<MarsRealState>


    @Query("SELECT * FROM mars_table WHERE id = :id")
    fun getMarsById(id: Int): LiveData<MarsRealState>


}
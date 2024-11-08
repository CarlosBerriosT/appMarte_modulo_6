package com.example.appMarte_6.Model.Local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appMarte_6.Model.Remote.MarsRealState


@Database(entities =[ MarsRealState::class] , version = 1)
abstract class MarsDatabase : RoomDatabase() {


    abstract fun marsDao(): MarsDao

    companion object {
        @Volatile
        private var INSTANCE: MarsDatabase? = null

        fun getDataBase(context: Context): MarsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MarsDatabase::class.java,
                    "terrain_marts"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
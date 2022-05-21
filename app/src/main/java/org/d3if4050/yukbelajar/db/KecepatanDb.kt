package org.d3if4050.yukbelajar.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [KecepatanEntity::class], version = 1, exportSchema = false)
abstract class KecepatanDb : RoomDatabase() {

    abstract val dao: KecepatanDao

    companion object{
        @Volatile
        private var INSTANCE: KecepatanDb? = null

        fun getInstance(context: Context): KecepatanDb{
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        KecepatanDb::class.java,
                    "yukBelajar,db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
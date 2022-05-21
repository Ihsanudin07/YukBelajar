package org.d3if4050.yukbelajar.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface KecepatanDao {

    @Insert
    fun insert(yukBelajar: KecepatanEntity)

    @Query("SELECT * FROM yukBelajar ORDER BY id DESC")
    fun getLastKecepatan(): LiveData<List<KecepatanEntity>>
}
package org.d3if4050.yukbelajar.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "yukBelajar")
data class KecepatanEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    val jarak: Float,
    var waktu: Float,
    var kecepatan: Float
)
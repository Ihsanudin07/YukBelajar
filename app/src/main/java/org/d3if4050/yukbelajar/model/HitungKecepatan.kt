package org.d3if4050.yukbelajar.model

import org.d3if4050.yukbelajar.db.KecepatanEntity

fun KecepatanEntity.hitungKecepatan(): HasilKecepatan{
    val hitungKec = jarak / waktu
    val hitungJar = kecepatan / waktu
    return HasilKecepatan(hitungKec, hitungJar)
}
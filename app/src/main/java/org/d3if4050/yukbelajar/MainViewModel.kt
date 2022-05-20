package org.d3if4050.yukbelajar

import androidx.lifecycle.ViewModel
import org.d3if4050.yukbelajar.model.HasilKecepatan

class MainViewModel : ViewModel() {
    public fun hitungKecepatan(jarak: Float, waktu: Float, kecepatan: Float): HasilKecepatan {
        val hitungKec = jarak / waktu
        val hitungJar = kecepatan / waktu

        return HasilKecepatan(hitungKec, hitungJar)
    }
}
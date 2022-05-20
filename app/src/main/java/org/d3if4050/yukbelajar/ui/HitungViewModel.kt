package org.d3if4050.yukbelajar.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if4050.yukbelajar.model.HasilKecepatan

class MainViewModel : ViewModel() {
    private val hasilKecepatan = MutableLiveData<HasilKecepatan?>()

    public fun hitungKecepatan(jarak: Float, waktu: Float, kecepatan: Float){
        val hitungKec = jarak / waktu
        val hitungJar = kecepatan / waktu

        hasilKecepatan.value = HasilKecepatan(hitungKec, hitungJar)
    }

    fun getHasilKecepatan(): LiveData<HasilKecepatan?> = hasilKecepatan
}
package org.d3if4050.yukbelajar.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if4050.yukbelajar.db.KecepatanDao
import org.d3if4050.yukbelajar.db.KecepatanEntity
import org.d3if4050.yukbelajar.model.HasilKecepatan

class HitungViewModel(private val db: KecepatanDao) : ViewModel(){

    private val hasilKecepatan = MutableLiveData<HasilKecepatan?>()

    val data = db.getLastKecepatan()

    public fun hitungKecepatan(jarak: Float, waktu: Float, kecepatan: Float){
        val hitungKec = jarak / waktu
        val hitungJar = kecepatan / waktu

        hasilKecepatan.value = HasilKecepatan(hitungKec, hitungJar)

        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val dataKecepatan = KecepatanEntity(
                    jarak = jarak,
                    waktu = waktu,
                    kecepatan = kecepatan
                )
                db.insert(dataKecepatan)
            }
        }
    }

    fun getHasilKecepatan(): LiveData<HasilKecepatan?> = hasilKecepatan
}
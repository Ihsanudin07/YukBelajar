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
import org.d3if4050.yukbelajar.model.hitungKecepatan

class HitungViewModel(private val db: KecepatanDao) : ViewModel(){

    private val hasilKecepatan = MutableLiveData<HasilKecepatan?>()

    public fun hitungKecepatan(jarak: Float, waktu: Float, kecepatan: Float){
        val dataKecepatan = KecepatanEntity(
            jarak = jarak,
            waktu = waktu,
            kecepatan = kecepatan
        )
        hasilKecepatan.value = dataKecepatan.hitungKecepatan()

        viewModelScope.launch {
            withContext(Dispatchers.IO){
                db.insert(dataKecepatan)
            }
        }
    }

    fun getHasilKecepatan(): LiveData<HasilKecepatan?> = hasilKecepatan
}
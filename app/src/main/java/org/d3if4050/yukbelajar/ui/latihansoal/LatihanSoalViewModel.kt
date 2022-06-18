package org.d3if4050.yukbelajar.ui.latihansoal

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if4050.yukbelajar.LatihanSoal
import org.d3if4050.yukbelajar.R
import org.d3if4050.yukbelajar.network.LatihanSoalApi
import java.lang.Exception

class LatihanSoalViewModel: ViewModel() {

    private val data = MutableLiveData<List<LatihanSoal>>()

    init {
        retrieveData()
    }

    private fun retrieveData(){
        viewModelScope.launch(Dispatchers.IO){
            try {
                data.postValue(LatihanSoalApi.service.getLatihanSoal())
            }catch (e: Exception){
                Log.d("LatihanSoalViewModel","Failure: ${e.message}")
            }
        }
    }

//    private fun initData(): List<LatihanSoal>{
//        return listOf(
//            LatihanSoal("1. Sebuah mobil melaju di jalan tol dengan kecepatan tetap. Dalam 2 jam, mobil tersebut sudah menempuh jarak 144 km. Kecepatan mobil adalah...", R.drawable.gambar1),
//            LatihanSoal("1. Sebuah mobil melaju ", R.drawable.gambar2),
//            LatihanSoal("1. Sebuah mobil melaju di jalan tol dengan kecepatan tetap. Dalam 2 jam, mobil tersebut sudah menempuh jarak 144 km. Kecepatan mobil adalah...", R.drawable.gambar3),
//            LatihanSoal("1. Sebuah mobil melaju di jalan tol dengan kecepatan tetap. Dalam 2 jam, mobil tersebut sudah menempuh jarak 144 km. Kecepatan mobil adalah...", R.drawable.gambar4),
//        )
//    }

    fun getData():LiveData<List<LatihanSoal>> = data
}
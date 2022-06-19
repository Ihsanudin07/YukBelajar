package org.d3if4050.yukbelajar.ui.latihansoal

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if4050.yukbelajar.LatihanSoal
import org.d3if4050.yukbelajar.MainActivity
import org.d3if4050.yukbelajar.R
import org.d3if4050.yukbelajar.network.ApiStatus
import org.d3if4050.yukbelajar.network.LatihanSoalApi
import org.d3if4050.yukbelajar.network.UpdateWorker
import java.lang.Exception
import java.util.concurrent.TimeUnit

class LatihanSoalViewModel: ViewModel() {

    private val status = MutableLiveData<ApiStatus>()
    private val data = MutableLiveData<List<LatihanSoal>>()

    init {
        retrieveData()
    }

    private fun retrieveData(){
        viewModelScope.launch(Dispatchers.IO){
            status.postValue(ApiStatus.LOADING)
            try {
                data.postValue(LatihanSoalApi.service.getLatihanSoal())
                status.postValue(ApiStatus.SUCCESS)
            }catch (e: Exception){
                Log.d("LatihanSoalViewModel","Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
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

    fun getStatus(): LiveData<ApiStatus> = status

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            MainActivity.CHANNEL_ID,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }

}
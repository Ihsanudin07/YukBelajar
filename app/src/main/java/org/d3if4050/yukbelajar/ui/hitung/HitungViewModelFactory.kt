package org.d3if4050.yukbelajar.ui.hitung

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if4050.yukbelajar.db.KecepatanDao
import org.d3if4050.yukbelajar.ui.HitungViewModel

class HitungViewModelFactory(
    private val db: KecepatanDao
): ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HitungViewModel::class.java)){
            return HitungViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
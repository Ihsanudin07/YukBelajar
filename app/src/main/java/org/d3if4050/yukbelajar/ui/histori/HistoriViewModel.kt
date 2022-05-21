package org.d3if4050.yukbelajar.ui.histori

import androidx.lifecycle.ViewModel
import org.d3if4050.yukbelajar.db.KecepatanDao

class HistoriViewModel(db: KecepatanDao) : ViewModel() {
    val data = db.getLastKecepatan()
}
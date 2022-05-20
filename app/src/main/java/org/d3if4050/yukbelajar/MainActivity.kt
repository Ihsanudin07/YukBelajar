package org.d3if4050.yukbelajar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import org.d3if4050.yukbelajar.databinding.ActivityMainBinding
import org.d3if4050.yukbelajar.model.HasilKecepatan

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonHitung.setOnClickListener{
            hitungKecepatan()
        }

        binding.buttonReset.setOnClickListener{
            resetInputan()
        }
    }

    private fun hitungKecepatan(){
        val jarak = binding.editJarakText.text.toString()
        if (TextUtils.isEmpty(jarak)){
            Toast.makeText(this, R.string.jarakKosong, Toast.LENGTH_LONG).show()
            return
        }

        val waktu = binding.editWaktuText.text.toString()
        if (TextUtils.isEmpty(waktu)){
            Toast.makeText(this, R.string.waktuKosong, Toast.LENGTH_LONG).show()
            return
        }

        val kecepatan = binding.editKecepatanText.text.toString()
        if (TextUtils.isEmpty(kecepatan)){
            Toast.makeText(this, R.string.kecepatanKosong, Toast.LENGTH_LONG).show()
            return
        }

        val result = hitungKecepatan(
            jarak.toFloat(),
            waktu.toFloat(),
            kecepatan.toFloat()
        )
        showResult(result)
    }

    private fun resetInputan(){
        binding.editJarakText.setText("")
        binding.editWaktuText.setText("")
        binding.editKecepatanText.setText("")
    }

    private fun hitungKecepatan(jarak: Float, waktu: Float, kecepatan: Float): HasilKecepatan{
        val hitungKec = jarak / waktu
        val hitungJar = kecepatan / waktu

        return HasilKecepatan(hitungKec, hitungJar)
    }

    private fun showResult(result: HasilKecepatan){
        binding.textKecepatan.text = getString(R.string.hasilkecepatan, result.kecepatan)
        binding.textHasilJarak.text = getString(R.string.hasiljarak, result.jarak)
    }
}
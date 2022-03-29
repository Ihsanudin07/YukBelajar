package org.d3if4050.yukbelajar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.d3if4050.yukbelajar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonHitung.setOnClickListener{
            hitungKecepatan()
        }
    }

    private fun hitungKecepatan(){
        Log.d("MainActivity", "Tombol diklik!")
    }
}
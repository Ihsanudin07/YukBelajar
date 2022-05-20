package org.d3if4050.yukbelajar.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.d3if4050.yukbelajar.R
import org.d3if4050.yukbelajar.databinding.FragmentHitungBinding
import org.d3if4050.yukbelajar.model.HasilKecepatan

class HitungFragment : Fragment() {

    private lateinit var binding: FragmentHitungBinding
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonHitung.setOnClickListener{
            hitungKecepatan()
        }

        binding.buttonReset.setOnClickListener{
            resetInputan()
        }

        viewModel.getHasilKecepatan().observe(requireActivity(), { showResult(it)})
    }

    private fun hitungKecepatan(){
        val jarak = binding.editJarakText.text.toString()
        if (TextUtils.isEmpty(jarak)){
            Toast.makeText(context, R.string.jarakKosong, Toast.LENGTH_LONG).show()
            return
        }

        val waktu = binding.editWaktuText.text.toString()
        if (TextUtils.isEmpty(waktu)){
            Toast.makeText(context, R.string.waktuKosong, Toast.LENGTH_LONG).show()
            return
        }

        val kecepatan = binding.editKecepatanText.text.toString()
        if (TextUtils.isEmpty(kecepatan)){
            Toast.makeText(context, R.string.kecepatanKosong, Toast.LENGTH_LONG).show()
            return
        }

        viewModel.hitungKecepatan(
            jarak.toFloat(),
            waktu.toFloat(),
            kecepatan.toFloat()
        )
    }

    private fun resetInputan(){
        binding.editJarakText.setText("")
        binding.editWaktuText.setText("")
        binding.editKecepatanText.setText("")
    }

    private fun showResult(result: HasilKecepatan?){
        if (result == null) return
        binding.textKecepatan.text = getString(R.string.hasilkecepatan, result.kecepatan)
        binding.textHasilJarak.text = getString(R.string.hasiljarak, result.jarak)
    }
}
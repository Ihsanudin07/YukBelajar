package org.d3if4050.yukbelajar.ui.hitung

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import org.d3if4050.yukbelajar.R
import org.d3if4050.yukbelajar.databinding.FragmentHitungBinding
import org.d3if4050.yukbelajar.db.KecepatanDb
import org.d3if4050.yukbelajar.model.HasilKecepatan
import org.d3if4050.yukbelajar.ui.HitungViewModel

class HitungFragment : Fragment() {

    private lateinit var binding: FragmentHitungBinding
    private val viewModel: HitungViewModel by lazy {
        val db = KecepatanDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonHitung.setOnClickListener{
            hitungKecepatan()
        }

        binding.buttonReset.setOnClickListener{
            resetInputan()
        }

        binding.rumusButton.setOnClickListener{
            it.findNavController().navigate(
                R.id.action_hitungFragment_to_rumusFragment
            )
        }

        binding.shareButton.setOnClickListener { shareData() }

        viewModel.getHasilKecepatan().observe(requireActivity(), { showResult(it)})
        viewModel.data.observe(viewLifecycleOwner, {
            if (it == null) return@observe
            Log.d("HitungFragment", "Data tersimpan. ID = ${it.id}")
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_historiFragment
                )
                return true
            }
            R.id.menu_tentang ->{
                findNavController().navigate(
                    R.id.action_hitungFragment_to_aboutFragment
                )
                return true
            }
        }
        return super.onOptionsItemSelected(item)
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
        binding.buttonGroup.visibility = View.VISIBLE
    }

    private fun shareData(){
        val message = getString(R.string.bagikan_temp,
        binding.editJarakText.text,
        binding.editWaktuText.text,
        binding.editKecepatanText.text,
        binding.textKecepatan.text,
        binding.textHasilJarak.text)

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
                startActivity(shareIntent)
        }
    }
}
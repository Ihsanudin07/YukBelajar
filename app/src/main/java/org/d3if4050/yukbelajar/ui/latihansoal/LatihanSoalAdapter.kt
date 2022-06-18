package org.d3if4050.yukbelajar.ui.latihansoal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if4050.yukbelajar.LatihanSoal
import org.d3if4050.yukbelajar.R
import org.d3if4050.yukbelajar.databinding.ListSoalBinding
import org.d3if4050.yukbelajar.network.LatihanSoalApi

class LatihanSoalAdapter: RecyclerView.Adapter<LatihanSoalAdapter.ViewHolder>() {

    private val data = mutableListOf<LatihanSoal>()

    fun updateData(newData: List<LatihanSoal>){
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ListSoalBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(latihanSoal: LatihanSoal) = with(binding){
            soalTextView.text = latihanSoal.soal
            Glide.with(imageView.context)
                .load(LatihanSoalApi.getLatihanSoalUrl(latihanSoal.imageId))
                .error(R.drawable.ic_baseline_heart_broken_24)
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListSoalBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }


    override fun getItemCount(): Int {
        return data.size
    }
}
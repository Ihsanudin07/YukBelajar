package org.d3if4050.yukbelajar.ui.latihansoal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if4050.yukbelajar.databinding.TampilanListBinding

class LatihanSoalFragment :Fragment() {
    private val viewModel: LatihanSoalViewModel by lazy {
        ViewModelProvider(this).get(LatihanSoalViewModel::class.java)
    }

    private lateinit var myAdapter: LatihanSoalAdapter
    private lateinit var binding: TampilanListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TampilanListBinding.inflate(layoutInflater, container, false)
        myAdapter = LatihanSoalAdapter()
        with(binding.recyclerView){
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner, {
            myAdapter.updateData(it)
        })
    }
}
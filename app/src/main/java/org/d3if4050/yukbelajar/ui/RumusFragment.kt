package org.d3if4050.yukbelajar.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.d3if4050.yukbelajar.databinding.FragmentLihatrumusBinding

class RumusFragment : Fragment() {

    private lateinit var binding: FragmentLihatrumusBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLihatrumusBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}
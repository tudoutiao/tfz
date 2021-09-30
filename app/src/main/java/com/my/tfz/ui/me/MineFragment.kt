package com.my.tfz.ui.me

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.my.tfz.databinding.FragmentMineBinding
import com.my.tfz.ui.adapter.MineAdapter

class MineFragment : Fragment() {

    lateinit var viewModel: MineViewModel
    var _binding: FragmentMineBinding? = null
    val binding get() = _binding!!
    lateinit var adapter: MineAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(MineViewModel::class.java)
        _binding = FragmentMineBinding.inflate(inflater, container, false)
        adapter = MineAdapter()
        binding.recyclerView.adapter = adapter
        subscribeUi()
        return binding.root
    }

    private fun subscribeUi() {
        viewModel.dataList.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }

}
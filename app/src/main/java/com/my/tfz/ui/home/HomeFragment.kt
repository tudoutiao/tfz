package com.my.tfz.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.my.tfz.databinding.FragmentHomeBinding
import com.my.tfz.ui.adapter.HomeAppAdapter

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        var adapter =HomeAppAdapter()
        binding.girdView.adapter=adapter
//        binding.girdView.addItemDecoration(DividerItemDecoration(activity,DividerItemDecoration.VERTICAL))
        subscribeUi(adapter, binding)

        return binding.root
    }

    private fun subscribeUi(adapter: HomeAppAdapter, binding: FragmentHomeBinding) {
        homeViewModel.appInfoList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
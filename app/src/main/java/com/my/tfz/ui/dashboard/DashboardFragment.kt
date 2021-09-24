package com.my.tfz.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.my.tfz.databinding.FragmentDashboardBinding
import com.my.tfz.ui.adapter.ConstactAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val adapter: ConstactAdapter by lazy {
        ConstactAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        binding.recyclerView.adapter = adapter
        binding.alphabetView.setOnTouchLetterChangedListener { s ->
            var index = -1
            dashboardViewModel.constactList.value!!.map continuing@{
                index++
                if (it.pinyin!![0].toString().equals(s)) {
                    binding.recyclerView.scrollToPosition(index)
                    return@continuing
                }
            }
        }
        binding.alphabetView.setOnTouchLetterReleasedListener {

        }
        dashboardViewModel.constactList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            lifecycleScope.launchWhenResumed {
                // 1.创建通道
                val channel = Channel<Int>()
                // 2.向通道发送数据
                launch(Dispatchers.IO) {
                    for (i in 1..100) {
                        delay(150) // 延时0.15秒发送
                        channel.send(i)
                    }
                    channel.close()
                }
                // 3.通道接收数据
                launch(Dispatchers.IO) {
                    for (n in channel) {
                        activity?.runOnUiThread {
                            Log.e("", "----${n}-----------");
                            // n为channel.send(i)发送过来的数据
                        }
                    }
                }
            }
        })
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
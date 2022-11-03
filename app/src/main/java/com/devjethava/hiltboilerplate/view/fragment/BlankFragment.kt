package com.devjethava.hiltboilerplate.view.fragment

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devjethava.hiltboilerplate.R
import com.devjethava.hiltboilerplate.databinding.FragmentBlankBinding
import com.devjethava.hiltboilerplate.view.base.BaseFragment
import com.devjethava.hiltboilerplate.viewmodel.DashboardViewModel
import com.devjethava.hiltboilerplate.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class BlankFragment : BaseFragment<FragmentBlankBinding>(R.layout.fragment_blank) {

    private val TAG = BlankFragment::class.simpleName

    init {
        strTag = TAG!!
    }

    private val viewModel by viewModels<DashboardViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun initCreateView() {
        super.initCreateView()
        binding.fragment = this
        binding.vm = viewModel
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment BlankFragment.
         */
        @JvmStatic
        fun newInstance() =
            BlankFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}
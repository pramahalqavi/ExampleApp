package com.example.exampleapp.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.exampleapp.databinding.FragmentExampleBinding
import com.example.exampleapp.viewmodel.ExampleViewModel

class ExampleFragment: Fragment() {

    private var binding: FragmentExampleBinding? = null
    private val mBinding get() = binding!!
    private var mICommuncator: IExampleCommunicator? = null

    private val mViewModel: ExampleViewModel by activityViewModels<ExampleViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mICommuncator = context as IExampleCommunicator
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExampleBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.tvText.text = mViewModel.data
        mBinding.btMsg.setOnClickListener {
            mICommuncator?.communicate("Hi")
            mICommuncator?.changeFragment()
        }
    }

    interface IExampleCommunicator {
        fun communicate(message: String)
        fun changeFragment()
    }
}
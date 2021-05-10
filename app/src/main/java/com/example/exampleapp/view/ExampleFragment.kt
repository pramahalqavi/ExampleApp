package com.example.exampleapp.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exampleapp.adapter.PokeAdapter
import com.example.exampleapp.adapter.PokeTitleAdapter
import com.example.exampleapp.databinding.FragmentExampleBinding
import com.example.exampleapp.viewmodel.ExampleViewModel

class ExampleFragment: Fragment() {

    private var binding: FragmentExampleBinding? = null
    private val mBinding get() = binding!!
    private var mICommuncator: IExampleCommunicator? = null
    private var mPokeTitleAdapter: PokeTitleAdapter? = null
    private var mPokeAdapter: PokeAdapter? = null

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

    override fun onDestroyView() {
        super.onDestroyView()
        mICommuncator = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.tvText.text = mViewModel.message
        mBinding.btMsg.setOnClickListener {
            mICommuncator?.communicate("Hi")
            mICommuncator?.changeFragment()
        }
    }

    fun initAdapter() {
        mPokeTitleAdapter = PokeTitleAdapter("Pokemon", mViewModel.mPokemonList)
        mPokeAdapter = PokeAdapter(mViewModel.mPokemonList)
        mBinding.rvItems.layoutManager = LinearLayoutManager(context)
        mBinding.rvItems.adapter = mPokeTitleAdapter
    }

    interface IExampleCommunicator {
        fun communicate(message: String)
        fun changeFragment()
    }
}
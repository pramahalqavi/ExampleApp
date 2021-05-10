package com.example.exampleapp.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.exampleapp.R
import com.example.exampleapp.databinding.ActivityExampleBinding
import com.example.exampleapp.model.Pokemon
import com.example.exampleapp.model.ResponseResult
import com.example.exampleapp.viewmodel.ExampleViewModel

class ExampleActivity: AppCompatActivity(), ExampleFragment.IExampleCommunicator {
    private lateinit var mBinding: ActivityExampleBinding
    private val mViewModel: ExampleViewModel by viewModels<ExampleViewModel>()
    var mFragment: ExampleFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityExampleBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        observeProgressBar()
        initializeView()
    }

    private fun initializeView() {
        with(mBinding) {
            tvTitle.text = "Call api"
            btFunction.setOnClickListener {
                callPokeApi()
            }
            mFragment = ExampleFragment().apply {
                supportFragmentManager.beginTransaction().replace(R.id.fcv_container, this)
                    .commit()
            }
        }
    }

    private fun observeProgressBar() {
        mViewModel.isLoading.observe(this, Observer {
            mBinding.pbLoading.root.visibility = if (it == true) View.VISIBLE else View.GONE
        })
    }

    private fun callPokeApi() {
        mViewModel.isLoading.value = true
        mViewModel.callPokeApi().observe(this, Observer { responseResult ->
            if (responseResult.status == ResponseResult.Status.SUCCESS) {
                mViewModel.isLoading.value = false
                with(mBinding) {
                    mViewModel.mPokemonList.clear()
                    mViewModel.mPokemonList.addAll(
                        (responseResult.data?.results as? ArrayList<Pokemon>).orEmpty())
                    mFragment?.initAdapter()
                }
            } else {
                mViewModel.isLoading.value = false
            }
        })
    }

    override fun communicate(message: String) {
        mBinding.tvTitle.text = message
    }

    override fun changeFragment() {

    }
}

package com.example.exampleapp.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.exampleapp.R
import com.example.exampleapp.databinding.ActivityExampleBinding
import com.example.exampleapp.model.ResponseResult
import com.example.exampleapp.viewmodel.ExampleViewModel

class ExampleActivity: AppCompatActivity(), ExampleFragment.IExampleCommunicator {
    private lateinit var mBinding: ActivityExampleBinding
    private val mViewModel: ExampleViewModel by viewModels<ExampleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityExampleBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initializeView()
    }

    private fun initializeView() {
        with(mBinding) {
            tvTitle.text = "Call api"
            btFunction.setOnClickListener {
                callPokeApi()
            }
            mViewModel.data = "B"
            val mFragment = ExampleFragment()
            supportFragmentManager.beginTransaction().replace(R.id.fcv_container, mFragment)
                .commit()
        }
    }

    private fun callPokeApi() {
        mBinding.tvTitle.text = "loading.."
        mViewModel.callPokeApi().observe(this, Observer { responseResult ->
            if (responseResult.status == ResponseResult.Status.SUCCESS) {
                with(mBinding) {
                    tvTitle.text = "success"
                    tvData.visibility = View.VISIBLE
                    tvData.text = ""
                    responseResult.data?.results?.forEach {
                        tvData.text = "${tvData.text} ${it.name}"
                    }

                }
            } else {
                mBinding.tvTitle.text = "error"
            }
        })
    }

    override fun communicate(message: String) {
        mBinding.tvTitle.text = message
    }

    override fun changeFragment() {

    }
}

package com.example.exampleapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exampleapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  private lateinit var mBinding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    mBinding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(mBinding.root)
    initView()
  }

  private fun initView() {
    mBinding.btClickMe.setOnClickListener {
      startActivity(Intent(this, ExampleActivity::class.java))
    }
  }

}
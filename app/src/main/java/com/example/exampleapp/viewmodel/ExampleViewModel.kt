package com.example.exampleapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.exampleapp.model.PokeResponse
import com.example.exampleapp.model.Pokemon
import com.example.exampleapp.model.ResponseResult
import com.example.exampleapp.repository.ExampleRepository

class ExampleViewModel: ViewModel() {
    private val mRepository: ExampleRepository = ExampleRepository()
    var data = "A"

    fun callPokeApi(): LiveData<ResponseResult<PokeResponse<List<Pokemon>>>> = mRepository.callPokeApi()

}
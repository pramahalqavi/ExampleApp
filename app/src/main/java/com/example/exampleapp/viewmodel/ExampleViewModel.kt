package com.example.exampleapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exampleapp.model.PokeResponse
import com.example.exampleapp.model.Pokemon
import com.example.exampleapp.model.ResponseResult
import com.example.exampleapp.repository.ExampleRepository

class ExampleViewModel: ViewModel() {
    private val mRepository: ExampleRepository = ExampleRepository()
    var message = "A"
    var isLoading = MutableLiveData<Boolean>(false)
    val mPokemonList by lazy { arrayListOf<Pokemon>() }

    fun callPokeApi(): LiveData<ResponseResult<PokeResponse<List<Pokemon>>>> = mRepository.callPokeApi()

}
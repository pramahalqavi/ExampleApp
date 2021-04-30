package com.example.exampleapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.exampleapp.model.PokeResponse
import com.example.exampleapp.model.Pokemon
import com.example.exampleapp.model.ResponseResult
import com.example.exampleapp.network.ExampleApi
import com.example.exampleapp.utils.NetworkManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class ExampleRepository {
    private val mApi: ExampleApi? = NetworkManager.getRetrofit().create(ExampleApi::class.java)
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    fun callPokeApi(): LiveData<ResponseResult<PokeResponse<List<Pokemon>>>> {
        val mutableLiveData = MutableLiveData<ResponseResult<PokeResponse<List<Pokemon>>>>()
        mCompositeDisposable.add(mApi?.getPokemons()?.subscribeOn(Schedulers.io())?.observeOn(
            AndroidSchedulers.mainThread())
            ?.subscribe({
                mutableLiveData.value = ResponseResult(ResponseResult.Status.SUCCESS, null, it)
            }, {
                Timber.e(it)
                mutableLiveData.value = ResponseResult(ResponseResult.Status.ERROR, null, null)
            }))
        return mutableLiveData
    }
}
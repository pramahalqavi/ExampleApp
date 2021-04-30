package com.example.exampleapp.network

import com.example.exampleapp.model.PokeResponse
import com.example.exampleapp.model.Pokemon
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ExampleApi {
        @GET("pokemon")
        fun getPokemons(): Observable<PokeResponse<List<Pokemon>>>

}
package com.example.firstapplication3aufacourses2.presentation

import com.example.firstapplication3aufacourses2.presentation.api.ChampionApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Singletons {
    companion object{
        val championApi: ChampionApi = Retrofit.Builder()
        .baseUrl("https://valorant-api.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ChampionApi::class.java)
    }
}
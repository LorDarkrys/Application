package com.example.firstapplication3aufacourses2.presentation.api


import com.example.firstapplication3aufacourses2.presentation.list.ChampionListFragment
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path


interface ChampionApi{
   // @GET("champion.json")
    //fun getChampionList(): Call<ChampionResponse>
    @GET("agents")
    fun getChampionList(): Call<ChampionListResponse>

    @GET("agents/{agentUuid}")
    fun getChampionDetail(@Path("agentUuid")agentUuid:String): Call<ChampionDetailResponse>

}
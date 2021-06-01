package com.example.firstapplication3aufacourses2.presentation.api

import com.example.firstapplication3aufacourses2.presentation.list.Champion
import java.net.HttpURLConnection

data class ChampionDetailResponse(

        val data : List<ChampionAbilities>

)

data class ChampionAbilities(
        val abilities: List<abilities>,
        var displayName : String,
        var description : String



)

data class abilities(
        val displayName : String,
        val description : String,
        val displayIcon : String

)
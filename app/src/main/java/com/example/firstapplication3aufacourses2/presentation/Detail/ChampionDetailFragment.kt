package com.example.firstapplication3aufacourses2.presentation.Detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.firstapplication3aufacourses2.R
import com.example.firstapplication3aufacourses2.presentation.Constant.Companion.uuid
import com.example.firstapplication3aufacourses2.presentation.Singletons
import com.example.firstapplication3aufacourses2.presentation.api.ChampionAbilities
import com.example.firstapplication3aufacourses2.presentation.api.ChampionDetailResponse
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ChampionDetailFragment : Fragment() {

    private lateinit var textViewName: TextView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_champion_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewName=view.findViewById(R.id.champion_detail_name)
     callApi()
    }

    private fun callApi(){
        val id = arguments?.getInt("championId") ?: -1
        val test=uuid[id]
        val test2=uuid[0]
        val test3=test+test2
        val single= Singletons.championApi.getChampionDetail(uuid[id])
        single.enqueue(object: Callback<ChampionDetailResponse>{
            override fun onFailure(call: Call<ChampionDetailResponse>, t: Throwable) {
                Snackbar.make(
                        requireView(),
                        "API connection issues",
                        Snackbar.LENGTH_SHORT
                )
                        .setAction("Action", null).show()

                //TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<ChampionDetailResponse>, response: Response<ChampionDetailResponse>) {
                if (response.isSuccessful && response.body() != null){
                    val data: List<ChampionAbilities> = response.body()!!.data
                    showList(data,id)



                }
            }

        })
    }

    private fun showList(data: List<ChampionAbilities>,id:Int) {
        textViewName.text = data[id].displayName
    }
}
package com.example.firstapplication3aufacourses2.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapplication3aufacourses2.R
import com.example.firstapplication3aufacourses2.presentation.Singletons
import com.example.firstapplication3aufacourses2.presentation.api.ChampionListResponse
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ChampionListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    private val adapter = ChampionAdapter(listOf(), ::onClickedChampion)
    private val layoutManager = LinearLayoutManager(context)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_champion_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.champion_recyclerview)

        recyclerView.apply {
         layoutManager=this@ChampionListFragment.layoutManager
         adapter= this@ChampionListFragment.adapter
        }


        Singletons.championApi.getChampionList().enqueue(object: Callback<ChampionListResponse> {
            override fun onFailure(call: Call<ChampionListResponse>, t: Throwable) {
                Snackbar.make(
                    requireView(),
                    "API connection issues",
                    Snackbar.LENGTH_SHORT
                )
                    .setAction("Action", null).show()

            //TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<ChampionListResponse>, response: Response<ChampionListResponse>) {
               if (response.isSuccessful && response.body() != null){
                   val championResponse : ChampionListResponse = response.body()!!
                   adapter.updateList(championResponse.data)
               }
            }
        })




    }
    private fun onClickedChampion(id: Int){
        findNavController().navigate(R.id.navigatetoChampionDetailFragment, bundleOf(
                "championId" to id
        ))
    }
}
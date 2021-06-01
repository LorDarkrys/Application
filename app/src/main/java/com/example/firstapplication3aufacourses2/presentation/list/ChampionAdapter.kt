package com.example.firstapplication3aufacourses2.presentation.list

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.firstapplication3aufacourses2.R


class ChampionAdapter(private var dataSet: List<Champion>, var listener: ((Int) -> Unit)? = null)  : RecyclerView.Adapter<ChampionAdapter.ViewHolder>() {


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.champion_name)
        val championImage: ImageView = view.findViewById(R.id.champion_image)
        val description: TextView = view.findViewById(R.id.champion_description)


    }

    fun updateList(list: List<Champion>){
        dataSet = list
        notifyDataSetChanged()
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.champion_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val champion : Champion = dataSet[position]
        viewHolder.textView.text = champion.displayName
        viewHolder.description.text = champion.description
        val image: String = champion.displayIcon
        Glide
                .with(viewHolder.itemView.context)
                .load(image)
                .apply(RequestOptions().override(400))
                .into(viewHolder.championImage)

        viewHolder.itemView.setOnClickListener {
            listener?.invoke(position)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
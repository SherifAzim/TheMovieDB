package com.sherif.themoviedb.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sherif.themoviedb.R
import com.sherif.themoviedb.models.TopRatedMovies

class RecycleListAdapter(private val context: Context, private val topRatedList: TopRatedMovies) :
    RecyclerView.Adapter<RecycleListAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title = itemView.findViewById<TextView>(R.id.title)
        val desc = itemView.findViewById<TextView>(R.id.desc)
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val viewHolder =
            LayoutInflater.from(context).inflate(R.layout.recycler_list_item, parent, false)

        return MyViewHolder(viewHolder)

    }

    override fun getItemCount(): Int {
        return topRatedList.results.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val title: String =
            "${topRatedList.results[position].title} /n ${topRatedList.results[position].original_title} "
        holder.title.text = title
        holder.desc.text = topRatedList.results[position].overview
    }

}
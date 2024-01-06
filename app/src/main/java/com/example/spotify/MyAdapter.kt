package com.example.spotify

import android.app.Activity
import android.media.MediaPlayer
import android.telecom.Call
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
// Import Picasso in your code
import com.squareup.picasso.Picasso



class MyAdapter(val context : Activity , val datalist :List<Data>): RecyclerView.Adapter<MyAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemview = LayoutInflater.from(context).inflate(R.layout.each_item , parent , false)
        return MyViewHolder(itemview)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //populate the data in view
        val current = datalist[position]
        holder.title.text = current.title

        val mediaPlayer = MediaPlayer.create(context, current.preview.toUri())
        holder.play.setOnClickListener {
            mediaPlayer.start()
        }
        holder.pause.setOnClickListener {
            mediaPlayer.stop()
        }

        Picasso.get().load(current.album.cover).into(holder.image);







    }
    class MyViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {
        //create the view in case layout manager fail to create the view
        val image : ImageView
        val title: TextView
        val play : ImageButton
        val pause : ImageButton

        init {
            image = itemView.findViewById(R.id.imageview3)
            title = itemView.findViewById(R.id.textView)
            play = itemView.findViewById(R.id.play)
            pause = itemView.findViewById(R.id.pause)

        }

    }

}




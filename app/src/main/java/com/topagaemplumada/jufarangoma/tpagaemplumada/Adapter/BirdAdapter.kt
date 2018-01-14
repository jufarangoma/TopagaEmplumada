package com.topagaemplumada.jufarangoma.tpagaemplumada.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.topagaemplumada.jufarangoma.tpagaemplumada.Models.Bird
import com.topagaemplumada.jufarangoma.tpagaemplumada.R
import com.topagaemplumada.jufarangoma.tpagaemplumada.Views.MainActivity

/**
 * Created by root on 1/14/18.
 */
class BirdAdapter(val birds: ArrayList<Bird>,val resource: Int, val activity: MainActivity): RecyclerView.Adapter<BirdAdapter.BirdViewHolder> (){

    override fun onBindViewHolder(holder: BirdViewHolder?, position: Int) {
        holder!!.setBird(birds.get(position))
    }

    override fun getItemCount(): Int {
        return birds.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BirdViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(resource,parent,false)
        return BirdViewHolder(view, activity)
    }

    class BirdViewHolder(var view: View,val activity: MainActivity): RecyclerView.ViewHolder(view){
        var name = view.findViewById<TextView>(R.id.tv_bird_name)
        var scientificName = view.findViewById<TextView>(R.id.tv_scientific_name)
        var birdImage = view.findViewById<ImageView>(R.id.iv_bird_image)

        fun setBird(bird:Bird){
            name.text = bird.name
            scientificName.text = bird.scientificName
            //birdImage.
            Glide.with(activity).load(bird.photo).into(birdImage)
        }
    }
}
package com.topagaemplumada.jufarangoma.tpagaemplumada.Adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.topagaemplumada.jufarangoma.tpagaemplumada.Models.Bird
import com.topagaemplumada.jufarangoma.tpagaemplumada.R
import com.topagaemplumada.jufarangoma.tpagaemplumada.Views.BirdProfile
import com.topagaemplumada.jufarangoma.tpagaemplumada.Views.MainActivity
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

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
        return BirdViewHolder(view, resource,activity)
    }

    class BirdViewHolder(var view: View,val resource: Int, val activity: MainActivity): RecyclerView.ViewHolder(view){
        var name = view.findViewById<TextView>(R.id.tv_bird_name)
        var scientificName = view.findViewById<TextView>(R.id.tv_scientific_name)
        var birdImage = view.findViewById<ImageView>(R.id.iv_bird_image)

        fun setBird(bird:Bird){
            name.text = bird.name
            scientificName.text = bird.scientificName
            Glide.with(activity).load(bird.photo).into(birdImage)
            view.setOnClickListener {
                activity.startActivity<BirdProfile>("Bird" to bird)
                /*val currenDialog = BirdProfile.newInstance(bird)
                val ft = activity.fragmentManager.beginTransaction()
                ft.addToBackStack(null)
                currenDialog.show(ft, "bird")*/
            }

        }

    }
}
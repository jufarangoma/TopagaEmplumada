package com.topagaemplumada.jufarangoma.tpagaemplumada.Views

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.topagaemplumada.jufarangoma.tpagaemplumada.Models.Bird
import com.topagaemplumada.jufarangoma.tpagaemplumada.R
import kotlinx.android.synthetic.main.activity_bird_profile.*
import kotlinx.android.synthetic.main.card_profile.*

/**
 * Created by root on 1/14/18.
 */
class BirdProfile:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bird_profile)
        val bird = intent.getSerializableExtra("Bird") as Bird
        Glide.with(this).load(bird.photo).into(iv_bird_profile)
        tv_name_profile.text = bird.name
        tv_scientific_name_profile.text = bird.scientificName
        description_profile.text = bird.description
        }
}
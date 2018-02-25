package com.topagaemplumada.jufarangoma.tpagaemplumada.Views

import android.app.DialogFragment
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.topagaemplumada.jufarangoma.tpagaemplumada.Models.Bird
import com.topagaemplumada.jufarangoma.tpagaemplumada.R
import kotlinx.android.synthetic.main.activity_bird_profile.*
import kotlinx.android.synthetic.main.card_profile.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by root on 1/14/18.
 */
class BirdProfile: AppCompatActivity(){

    var bird: Bird?=null
    var mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bird_profile)
        //setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog)
        bird = intent.getSerializableExtra("Bird") as Bird

    }

    override fun onResume() {
        super.onResume()
        Glide.with(this).load(bird!!.photo).into(iv_bird_profile)
        tv_name_profile.text = bird!!.name
        tv_scientific_name_profile.text = bird!!.scientificName
        description_profile.text = bird!!.description
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mediaPlayer.setDataSource(bird!!.audio)
        var isPlaying = false
        iv_bird_profile.setOnClickListener {
            if (!isPlaying) {
                isPlaying = true
                try {
                    mediaPlayer.prepare()
                    mediaPlayer.start()
                } catch (e: IllegalArgumentException) {
                }

            } else {
                mediaPlayer.stop()
                isPlaying = false
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.stop()
    }
}
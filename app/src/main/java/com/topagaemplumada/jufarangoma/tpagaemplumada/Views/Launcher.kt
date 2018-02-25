package com.topagaemplumada.jufarangoma.tpagaemplumada.Views

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.topagaemplumada.jufarangoma.tpagaemplumada.R
import org.jetbrains.anko.startActivity

/**
 * Created by root on 2/25/18.
 */
class Launcher:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        goMain()
    }
    fun goMain(){
        Thread{
            try {
                Thread.sleep(2000)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            startActivity<MainActivity>()
            this.finish()
        }.start()
    }
}
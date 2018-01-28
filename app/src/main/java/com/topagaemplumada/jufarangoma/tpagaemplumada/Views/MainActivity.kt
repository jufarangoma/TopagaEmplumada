package com.topagaemplumada.jufarangoma.tpagaemplumada.Views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.topagaemplumada.jufarangoma.tpagaemplumada.Models.Bird
import com.topagaemplumada.jufarangoma.tpagaemplumada.R
import com.google.firebase.database.DatabaseError
import com.google.firebase.internal.FirebaseAppHelper.getUid
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {

    var firebaseDatabase: FirebaseDatabase?=null
    var databaseReference: DatabaseReference?=null
    var birds = ArrayList<Bird>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var layoutManager = LinearLayoutManager(this)
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL)
        rv_birds.setLayoutManager(layoutManager)
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase!!.getReference().child("birds")
        databaseReference!!.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (child in snapshot.children) {
                    val birdHash = (child.value as HashMap<*,String>)
                    val bird = Bird(birdHash["name"].toString(),
                                    birdHash["scientificName"].toString(),
                                    birdHash["photo"].toString(),
                                    birdHash["audio"].toString(),
                                    birdHash["description"].toString())
                    print(bird)
                    birds.add(bird!!)
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })

    }
    fun uploadBird(view: View){
        startActivity<UploadBird>()
    }
}

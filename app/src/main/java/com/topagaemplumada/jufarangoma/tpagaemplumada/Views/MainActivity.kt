package com.topagaemplumada.jufarangoma.tpagaemplumada.Views

import android.app.ProgressDialog
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
import com.topagaemplumada.jufarangoma.tpagaemplumada.Adapter.BirdAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {

    var firebaseDatabase: FirebaseDatabase?=null
    var databaseReference: DatabaseReference?=null
    var birds = ArrayList<Bird>()
    private var progressDialog: ProgressDialog? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation= LinearLayoutManager.VERTICAL
        rv_birds.layoutManager= layoutManager
        showProgress("Cargando")
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
                    birds.add(bird)
                }
                firebaseListener(birds)
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }

        })
    }

    override fun onResume() {
        super.onResume()

    }
    fun uploadBird(view: View){
        startActivity<UploadBird>()
    }
    fun firebaseListener(birds: ArrayList<Bird>){
        closeProgress()
        if(birds.isNotEmpty()){
            val adapter = BirdAdapter(birds,R.layout.card_profile,this)

            rv_birds.adapter = adapter
        }

    }
    fun showProgress(titleDialog :String?){
        if(progressDialog!=null){
            closeProgress()
        }
        progressDialog= ProgressDialog(this,R.style.AppCompatAlertDialogStyle)
        progressDialog!!.setMessage(titleDialog)
        progressDialog!!.setCancelable(true)
        progressDialog!!.show()
    }

    fun closeProgress(){
        if (progressDialog != null && progressDialog!!.isShowing()) {
            progressDialog!!.dismiss()
            progressDialog=null
        }
    }

    fun reload(view:View){
        this.finish()
        startActivity<MainActivity>()
    }
}

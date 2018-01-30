package com.topagaemplumada.jufarangoma.tpagaemplumada.Views

import android.media.MediaPlayer
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.firebase.database.DatabaseReference
import com.topagaemplumada.jufarangoma.tpagaemplumada.R
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import org.jetbrains.anko.sdk25.coroutines.onClick
import com.google.firebase.storage.UploadTask
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.StorageReference
import android.R.attr.data
import android.widget.Toast
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageMetadata
import com.topagaemplumada.jufarangoma.tpagaemplumada.Models.Bird
import kotlinx.android.synthetic.main.activity_upload_bird.*
import org.jetbrains.anko.startActivity


/**
 * Created by root on 1/14/18.
 */
class UploadBird:AppCompatActivity(){

    val RC_PHOTO_PICKER = 2
    val RC_AUDIO_PICKER = 27

    var audio: String?=null
    var image: String?=null

    var storageReference: StorageReference?=null
    var firebaseStorage: FirebaseStorage? = null
    var firebaseDatabase: FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null
    var childEventListener: ChildEventListener? = null
    var storageMetadata: StorageMetadata?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_bird)
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase!!.getReference().child("birds");
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage!!.getReference().child("birds_photos");
        storageMetadata = StorageMetadata.Builder().setContentType("audio/mpeg").build()
    }

    fun uploadImage(view:View){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER)
    }

    fun uploadSound(view: View){
        val intent_upload = Intent()
        intent_upload.type = "audio/*"
        intent_upload.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent_upload, RC_AUDIO_PICKER)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            RC_PHOTO_PICKER->{
                val selectedImage = data!!.getData()
                val photoRef = storageReference!!.child(selectedImage.lastPathSegment)
                photoRef.putFile(selectedImage).addOnSuccessListener{ taskSnapshot ->
                    val download = taskSnapshot.downloadUrl
                    image = download!!.toString()
                    Toast.makeText(this,"Image upload Succesfully",Toast.LENGTH_SHORT).show()
                }

            }
            RC_AUDIO_PICKER->{
                val selectedAudio = data!!.getData()
                val audioRef = storageReference!!.child(selectedAudio.lastPathSegment)
                audioRef.putFile(selectedAudio,storageMetadata!!).addOnSuccessListener{ taskSnapshot ->
                    val download = taskSnapshot.downloadUrl
                    audio = download!!.toString()
                    Toast.makeText(this,"Audio upload Succesfully",Toast.LENGTH_SHORT).show()
                }
            }
            else ->{

            }
        }

    }

    fun uploadBird(view: View){
        val bird = Bird(et_common_name.text.toString(),et_scientific_name.text.toString(),image,audio,et_description.text.toString())
        databaseReference!!.push().setValue(bird)
        this.finish()
        startActivity<UploadBird>()
    }
}


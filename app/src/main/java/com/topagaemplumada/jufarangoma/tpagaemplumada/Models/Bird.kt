package com.topagaemplumada.jufarangoma.tpagaemplumada.Models

import java.io.Serializable

/**
 * Created by Juan Felipe Arango on 1/6/18.
 */
class Bird: Serializable{
    var name: String?=null
    var scientificName:String?=null
    var photo: String?=null
    var audio: String?=null
    var description: String?=null

    constructor(name: String,scientificName:String?,  photo: String?, audio: String?,description: String?){
        this.audio = audio
        this.description = description
        this.scientificName = scientificName
        this.photo = photo
        this.name = name
    }
}
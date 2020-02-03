package com.jca.myapplication.model

data class User (
    val id: Int, // database entity
    var name: String,
    val birthdate: String
)
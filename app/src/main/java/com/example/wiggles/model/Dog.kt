package com.example.wiggles.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

class Dog(
    val id: Int,
    val name: String,
    val age: Double,
    val gender: String,
    val color: String,
    val weight: Double,
    val location: String,
    val image: Int,
    val about: String,
    val owner: Owner
)
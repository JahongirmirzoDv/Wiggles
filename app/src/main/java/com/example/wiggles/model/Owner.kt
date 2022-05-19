package com.example.wiggles.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Owner(val name: String, val bio: String, val image: Int):Parcelable

package com.example.mygithubuserapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val username: String?,
    val name: String?,
    val company: String?,
    val location: String?,
    val followers: String?,
    val following: String?,
    val repo: String?,
    val photo: Int?
): Parcelable

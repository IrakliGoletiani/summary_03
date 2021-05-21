package com.example.sammary3

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(val firstName: String, val lastName: String, val email: String): Parcelable

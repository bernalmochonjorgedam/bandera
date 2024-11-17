package com.utad.banderas.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Autonomia(
    val id: Int,
    var nombre: String,
    val bandera: String = "spain"
) : Parcelable
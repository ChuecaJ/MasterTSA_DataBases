package es.usj.mastertsa.jchueca.cities.domain.model

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class City (val id: Int, val name: String, val description: String, val sunshineHours: Int): Parcelable
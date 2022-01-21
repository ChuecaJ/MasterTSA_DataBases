package es.usj.mastertsa.jchueca.cities.domain.model

import android.os.Parcelable


@kotlinx.parcelize.Parcelize
data class Sight (val city_id: Int, val sight_id: Int, val sight_name: String, val sight_description: String): Parcelable
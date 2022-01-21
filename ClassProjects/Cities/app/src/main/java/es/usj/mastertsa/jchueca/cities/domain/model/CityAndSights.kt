package es.usj.mastertsa.jchueca.cities.domain.model

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class CityAndSights(val city: City, val sights: List<Sight>): Parcelable

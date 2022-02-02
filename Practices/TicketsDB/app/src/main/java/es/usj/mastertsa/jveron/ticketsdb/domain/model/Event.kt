package es.usj.mastertsa.jveron.ticketsdb.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event (val id: Int, val price: Float, val name: String, val description: String): Parcelable
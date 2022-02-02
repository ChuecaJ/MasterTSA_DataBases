package es.usj.mastertsa.jveron.ticketsdb.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (
    val id: Int,
    val email: String,
    val password: String,
    val name: String
    ): Parcelable
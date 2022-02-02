package es.usj.mastertsa.jveron.ticketsdb.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserAndEvent (val user: User, val event: Event): Parcelable
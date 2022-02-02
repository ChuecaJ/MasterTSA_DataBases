package es.usj.mastertsa.jveron.ticketsdb.domain.model

data class UserAndEvents (
    val user: User,
    val events: List<Event>
    )